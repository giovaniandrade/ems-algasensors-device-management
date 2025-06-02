package com.algaworks.algasensors.device.management;

import io.hypersistence.tsid.TSID;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class TSIDTest {

    @Test
    void shouldGenerateTSID() {
        // System.setProperty("tsid.node", "2");
        // System.setProperty("tsid.node.count", "32");

        TSID tsid = IdGenerator.generateTSID();
        System.out.println(tsid);
        System.out.println(tsid.toLong());
        System.out.println(tsid.getInstant());

        assertThat(tsid.getInstant()).isCloseTo(Instant.now(), within(1, ChronoUnit.MINUTES));
    }

}
