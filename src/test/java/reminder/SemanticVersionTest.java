package reminder;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SemanticVersionTest {

    @Test
    void sameVersionIsNotBefore() {
        assertThat(version("1.0.1").isBefore(version("1.0.1"))).isFalse();
    }

    @Test
    void detectIfPatchVersionIsBefore() {
        assertThat(version("1.0.0").isBefore(version("1.0.1"))).isTrue();
    }

    @Test
    void detectIfMajorVersionIsBefore() {
        assertThat(version("1.9.9").isBefore(version("2.0.0"))).isTrue();
    }

    @Test
    void detectIfMinorVersionIsBefore() {
        assertThat(version("1.0.0").isBefore(version("1.1.0"))).isTrue();
    }

    @Test
    void majorVersionIsTheMostSignificantOne() {
        assertThat(version("2.0.0").isBefore(version("1.10.10"))).isFalse();
    }

    @Test
    void minorVersionIsMoreSignificantThanPatchVersion() {
        assertThat(version("2.2.0").isBefore(version("2.0.2"))).isFalse();
    }

    private SemanticVersion version(String versionString) {
        return SemanticVersion.parseFrom(versionString);
    }
}