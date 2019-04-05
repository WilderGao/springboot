package com.qg.springboot.shard.algorithm;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @author WilderGao
 * time 2019-04-05 10:18
 * motto : everything is no in vain
 * description
 */
public class ModuloDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Integer> {
    @Override
    public String doEqualSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        for (String each : collection) {
            int eachCharAt = each.charAt(each.length()-1) - 48;
            int value = shardingValue.getValue();
            if (eachCharAt == value) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Collection<Integer> values = shardingValue.getValues();
        for (Integer value : values) {
            for (String each : collection) {
                if (each.endsWith(value % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> collection, ShardingValue<Integer> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(collection.size());
        Range<Integer> range = shardingValue.getValueRange();
        for (Integer value = range.lowerEndpoint(); value <= range.upperEndpoint(); value++) {
            for (String each : collection) {
                if (each.endsWith(value % 2 + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
