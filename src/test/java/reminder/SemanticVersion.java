package reminder;

record SemanticVersion(int major, int minor, int patch) {
    public static SemanticVersion parseFrom(String versionString) {
        var split = versionString.split("\\.");
        var major = Integer.parseInt(split[0]);
        var minor = Integer.parseInt(split[1]);
        var patch = Integer.parseInt(split[2]);
        return new SemanticVersion(major, minor, patch);
    }

    public boolean isBefore(SemanticVersion other) {
        if(this.major > other.major) {
            return false;
        }
        if(this.major < other.major) {
            return true;
        }

        if(this.minor > other.minor) {
            return false;
        }
        if(this.minor < other.minor) {
            return true;
        }
        return this.patch < other.patch;
    }
}
