package touch.baton.infra.auth.oauth.github.response;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.Nullable;
import touch.baton.domain.member.vo.GithubUrl;
import touch.baton.domain.member.vo.ImageUrl;
import touch.baton.domain.member.vo.MemberName;
import touch.baton.domain.member.vo.OauthId;
import touch.baton.domain.member.vo.SocialId;
import touch.baton.domain.oauth.OauthInformation;
import touch.baton.domain.oauth.token.SocialToken;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;

@JsonNaming(SnakeCaseStrategy.class)
public record GithubMemberResponse(String id,
                                   @Nullable String name,
                                   String login,
                                   String htmlUrl,
                                   String avatarUrl
) {

    public OauthInformation toOauthInformation(final String accessToken) {
        return OauthInformation.builder()
                .socialToken(new SocialToken(accessToken))
                .oauthId(new OauthId(id))
                .memberName(new MemberName(name))
                .socialId(new SocialId(login))
                .githubUrl(new GithubUrl(htmlUrl))
                .imageUrl(new ImageUrl(avatarUrl))
                .build();
    }
}
