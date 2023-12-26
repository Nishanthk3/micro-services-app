package com.example.webapp.redis;

import io.lettuce.core.SocketOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.models.partitions.RedisClusterNode;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import io.lettuce.core.resource.Delay;
import io.lettuce.core.resource.DirContextDnsResolver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * https://docs.aws.amazon.com/AmazonElastiCache/latest/red-ug/BestPractices.Clients-lettuce-cme.html
 */
public class ElastiCacheUtil {

    protected static final Duration META_COMMAND_TIMEOUT = Duration.ofMillis(1000);
    protected static final Duration DEFAULT_COMMAND_TIMEOUT = Duration.ofMillis(250);
    // Socket connect timeout should be lower than command timeout for Lettuce
    protected static final Duration CONNECT_TIMEOUT = Duration.ofMillis(100);

    static ClusterClientOptions getClusterClientOptions() {
        TimeoutOptions timeoutOptions = TimeoutOptions.builder()
            .timeoutSource(new DynamicClusterTimeout(DEFAULT_COMMAND_TIMEOUT, META_COMMAND_TIMEOUT))
            .build();
        // Configure the topology refreshment optionts
        final ClusterTopologyRefreshOptions topologyOptions =
            ClusterTopologyRefreshOptions.builder()
                .enableAllAdaptiveRefreshTriggers()
                .enablePeriodicRefresh()
                .dynamicRefreshSources(true)
                .build();

        // Configure the socket options
        final SocketOptions socketOptions =
            SocketOptions.builder()
                .connectTimeout(CONNECT_TIMEOUT)
                .keepAlive(true)
                .build();

        // Configure the client's options
        final ClusterClientOptions clusterClientOptions =
            ClusterClientOptions.builder()
                .topologyRefreshOptions(topologyOptions)
                .socketOptions(socketOptions)
                .autoReconnect(true)
                .timeoutOptions(timeoutOptions)
                .nodeFilter(it ->
                    !(it.is(RedisClusterNode.NodeFlag.FAIL)
                        || it.is(RedisClusterNode.NodeFlag.EVENTUAL_FAIL)
                        || it.is(RedisClusterNode.NodeFlag.NOADDR)))
                .validateClusterNodeMembership(false)
                .build();
        return clusterClientOptions;
    }

    // Configure the client's resources
    static ClientResources getClientResources() {
        ClientResources clientResources =
            DefaultClientResources.builder()
                .dnsResolver(new DirContextDnsResolver())
                .reconnectDelay(
                    Delay.fullJitter(
                        Duration.ofMillis(100), // minimum 100 millisecond delay
                        Duration.ofSeconds(10), // maximum 10 second delay
                        100,
                        TimeUnit.MILLISECONDS)) // 100 millisecond base
                .build();
        return clientResources;
    }
}
