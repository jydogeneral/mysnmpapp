package com.jtt.utils;

import java.util.function.BiFunction;

import com.jtt.model.Sale;

@FunctionalInterface
public interface AdjustmentOperation extends BiFunction<Sale, Integer, Integer>{}