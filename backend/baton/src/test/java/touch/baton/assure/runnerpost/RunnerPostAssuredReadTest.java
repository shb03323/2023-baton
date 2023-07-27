package touch.baton.assure.runnerpost;

import org.junit.jupiter.api.Test;
import touch.baton.assure.fixture.MemberFixture;
import touch.baton.assure.fixture.RunnerFixture;
import touch.baton.config.AssuredTestConfig;
import touch.baton.domain.common.vo.Grade;
import touch.baton.domain.member.Member;
import touch.baton.domain.runner.Runner;
import touch.baton.domain.runnerpost.RunnerPost;
import touch.baton.domain.runnerpost.controller.response.RunnerPostResponse;
import touch.baton.fixture.domain.RunnerPostFixture;

import java.time.LocalDateTime;

import static touch.baton.fixture.vo.DeadlineFixture.deadline;
import static touch.baton.fixture.vo.TotalRatingFixture.totalRating;

@SuppressWarnings("NonAsciiCharacters")
class RunnerPostAssuredReadTest extends AssuredTestConfig {

    @Test
    void 러너의_게시글_식별자값으로_러너_게시글_상세_정보_조회에_성공한다() {
        final Member member = MemberFixture.from("헤나",
                "test@test.com",
                "1jgiwng9213n0f1",
                "https://",
                "우아한테크코스",
                "https://"
        );
        memberRepository.save(member);

        final Runner runner = RunnerFixture.from(member, totalRating(0), Grade.BARE_FOOT);
        runnerRepository.save(runner);

        final RunnerPost runnerPost = RunnerPostFixture.create(runner, deadline(LocalDateTime.now().plusHours(100)));
        runnerPostRepository.save(runnerPost);

        RunnerPostAssuredSupport
                .클라이언트_요청().러너_게시글_식별자값으로_러너_게시글을_조회한다(runnerPost.getId())
                .서버_응답().러너_게시글_단건_조회_성공을_검증한다(RunnerPostResponse.Detail.from(runnerPost));
    }
}
