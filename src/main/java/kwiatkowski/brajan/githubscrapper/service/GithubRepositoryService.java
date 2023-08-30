package kwiatkowski.brajan.githubscrapper.service;

import java.util.List;
import kwiatkowski.brajan.githubscrapper.dto.response.GithubRepositoryResponseDto;

/**
 * Service interface for interacting with GitHub repositories.
 */
public interface GithubRepositoryService {

  /**
   * Retrieves a list of non-fork GitHub repositories owned by a specific user.
   *
   * @param username The username of the GitHub user.
   * @return A list of GithubRepositoryResponseDto objects representing the user's repositories.
   */
  List<GithubRepositoryResponseDto> getAllUserRepositories(String username);
}
