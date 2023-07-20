package touch.baton.assure.runnerpost;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import touch.baton.assure.common.AssuredSupport;
import touch.baton.domain.runnerpost.controller.response.RunnerPostResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class RunnerPostAssuredSupport {

    private RunnerPostAssuredSupport() {
    }

    public static RunnerPostClientRequestBuilder 클라이언트_요청() {
        return new RunnerPostClientRequestBuilder();
    }

    public static class RunnerPostClientRequestBuilder {

        private ExtractableResponse<Response> response;

        public RunnerPostClientRequestBuilder 러너_게시글_식별자값으로_러너_게시글을_조회한다(final Long 러너_게시글_식별자값) {
            response = AssuredSupport.get("/api/v1/posts/runner/{runnerPostId}", "runnerPostId", 러너_게시글_식별자값);
            return this;
        }

        public RunnerPostClientRequestBuilder 러너_게시글_식별자값으로_러너_게시글을_삭제한다(final Long 러너_게시글_식별자값) {
            response = AssuredSupport.delete("/api/v1/posts/runner/{runnerPostId}", "runnerPostId", 러너_게시글_식별자값);
            return this;
        }

        public RunnerPostServerResponseBuilder 서버_응답() {
            return new RunnerPostServerResponseBuilder(response);
        }
    }

    public static class RunnerPostServerResponseBuilder {

        private final ExtractableResponse<Response> response;

        public RunnerPostServerResponseBuilder(final ExtractableResponse<Response> response) {
            this.response = response;
        }

        public void 러너_게시글_단건_조회_성공을_검증한다(final RunnerPostResponse.Single 러너_게시글_응답) {
            final RunnerPostResponse.Single actual = this.response.as(RunnerPostResponse.Single.class);

            assertAll(
                    () -> assertThat(actual.title()).isEqualTo(러너_게시글_응답.title()),
                    () -> assertThat(actual.contents()).isEqualTo(러너_게시글_응답.contents()),
                    () -> assertThat(actual.tags()).isEqualTo(러너_게시글_응답.tags()),
                    () -> assertThat(actual.deadLine()).isEqualToIgnoringSeconds(러너_게시글_응답.deadLine()),
                    () -> assertThat(actual.profile().name()).isEqualTo(러너_게시글_응답.profile().name()),
                    () -> assertThat(actual.profile().company()).isEqualTo(러너_게시글_응답.profile().company()),
                    () -> assertThat(actual.profile().imageUrl()).isEqualTo(러너_게시글_응답.profile().imageUrl()),
                    () -> assertThat(actual.profile().isOwner()).isEqualTo(러너_게시글_응답.profile().isOwner()),
                    () -> assertThat(actual.profile().memberId()).isEqualTo(러너_게시글_응답.profile().memberId()),
                    () -> assertThat(actual.chattingCount()).isEqualTo(러너_게시글_응답.chattingCount()),
                    () -> assertThat(actual.watchedCount()).isEqualTo(러너_게시글_응답.watchedCount()),
                    () -> assertThat(actual.runnerPostId()).isEqualTo(러너_게시글_응답.runnerPostId())
            );
        }

        public void 러너_게시글_삭제_성공을_검증한다(final HttpStatus HTTP_STATUS) {
            assertThat(response.statusCode())
                    .isEqualTo(HTTP_STATUS.value());
        }
    }
}
