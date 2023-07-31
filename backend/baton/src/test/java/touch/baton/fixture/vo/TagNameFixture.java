package touch.baton.fixture.vo;

import touch.baton.domain.common.vo.TagName;

public abstract class TagNameFixture {

    private TagNameFixture() {
    }

    public static TagName tagName(final String value) {
        return new TagName(value);
    }
}
