package com.example.webapp.redis;

import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.protocol.CommandType;
import io.lettuce.core.protocol.ProtocolKeyword;
import io.lettuce.core.protocol.RedisCommand;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * From https://docs.aws.amazon.com/AmazonElastiCache/latest/red-ug/BestPractices.Clients-lettuce-cme.html
 *
 * Use a dynamic timeout for commands, to avoid timeouts during cluster management and slow operations.
 */
class DynamicClusterTimeout extends TimeoutOptions.TimeoutSource {
    private static Set<ProtocolKeyword> COMMANDKEYWORD_TYPES = new HashSet<>();

    static {
        COMMANDKEYWORD_TYPES.add(CommandType.FLUSHDB);
        COMMANDKEYWORD_TYPES.add(CommandType.FLUSHALL);
        COMMANDKEYWORD_TYPES.add(CommandType.CLUSTER);
        COMMANDKEYWORD_TYPES.add(CommandType.INFO);
        COMMANDKEYWORD_TYPES.add(CommandType.KEYS);
    }

    private final Duration metaCommandTimeout;
    private final Duration defaultCommandTimeout;

    DynamicClusterTimeout(Duration defaultTimeout, Duration metaTimeout) {
        defaultCommandTimeout = defaultTimeout;
        metaCommandTimeout = metaTimeout;
    }

    @Override
    public long getTimeout(RedisCommand<?, ?, ?> command) {
        if (COMMANDKEYWORD_TYPES.contains(command.getType())) {
            return metaCommandTimeout.toMillis();
        }
        return defaultCommandTimeout.toMillis();
    }
}
