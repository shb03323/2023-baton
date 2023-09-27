package touch.baton.domain.runnerpost.query.repository;

import touch.baton.domain.runnerpost.command.RunnerPost;
import touch.baton.domain.runnerpost.command.vo.ReviewStatus;
import touch.baton.domain.tag.command.RunnerPostTag;
import touch.baton.domain.tag.command.vo.TagReducedName;

import java.util.List;

public interface RunnerPostCustomRepository {

    List<RunnerPost> pageByReviewStatusAndTagReducedName(final Long cursor,
                                                         final int limit,
                                                         final TagReducedName tagReducedName,
                                                         final ReviewStatus reviewStatus);

    List<RunnerPostTag> findRunnerPostTagsByRunnerPosts(final List<RunnerPost> runnerPosts);
}
