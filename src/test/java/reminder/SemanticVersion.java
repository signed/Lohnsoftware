package reminder;

import java.util.Comparator;

record SemanticVersion(int major, int minor, int patch) {
    private static final Comparator<SemanticVersion> comparator;

    static {
        var major = Comparator.comparing(SemanticVersion::major);
        var minor = Comparator.comparing(SemanticVersion::minor);
        var patch = Comparator.comparing(SemanticVersion::patch);
        comparator = major.thenComparing(minor).thenComparing(patch);
    }


    public static SemanticVersion parseFrom(String versionString) {
        var split = versionString.split("\\.");
        var major = Integer.parseInt(split[0]);
        var minor = Integer.parseInt(split[1]);
        var patch = Integer.parseInt(split[2]);
        return new SemanticVersion(major, minor, patch);
    }

    public boolean isBefore(SemanticVersion other) {
        return comparator.compare(this, other) < 0;
    }
}
