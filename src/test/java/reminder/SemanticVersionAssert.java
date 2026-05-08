package reminder;

import org.assertj.core.api.AbstractObjectAssert;

class SemanticVersionAssert extends AbstractObjectAssert<SemanticVersionAssert, SemanticVersion> {

    public static SemanticVersionAssert assertThat(SemanticVersion semanticVersion) {
        return new SemanticVersionAssert(semanticVersion);
    }

    public SemanticVersionAssert(SemanticVersion semanticVersion) {
        super(semanticVersion, SemanticVersionAssert.class);
    }

    public SemanticVersionAssert isBefore(String laterVersionString) {
        if (!actual().isBefore(SemanticVersion.parseFrom(laterVersionString))) {
            throw failure("actual is not before expected");
        }
        return this;
    }
}
