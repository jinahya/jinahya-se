package com.github.jinahya.time;

import java.time.ZoneId;
import java.util.Set;
import java.util.stream.Collectors;

public final class JinahyaZoneIds {

    public static Set<ZoneId> getAvailableZones() {
        return ZoneId.getAvailableZoneIds().stream().map(ZoneId::of).collect(Collectors.toSet());
    }

    private JinahyaZoneIds() {
        throw new AssertionError("instantiation is not allowed");
    }
}
