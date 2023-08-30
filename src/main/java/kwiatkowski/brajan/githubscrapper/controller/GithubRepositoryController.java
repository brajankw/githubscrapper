package kwiatkowski.brajan.githubscrapper.controller;

import java.util.List;
import kwiatkowski.brajan.githubscrapper.dto.response.GithubRepositoryResponseDto;
import kwiatkowski.brajan.githubscrapper.exception.HeaderNotValidException;
import kwiatkowski.brajan.githubscrapper.service.GithubRepositoryService;
import kwiatkowski.brajan.githubscrapper.util.ApiConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller class handles requests related to GitHub repositories.
 */
@Slf4j
@RequestMapping("/repositories")
@RestController
@RequiredArgsConstructor
public class GithubRepositoryController {

  private final GithubRepositoryService githubRepositoryService;

  /**
   * Retrieves a list of GitHub repositories owned by a specific user.
   *
   * @param header   The Accept header specifying the expected response format (should be "application/json").
   * @param username The username of the GitHub user.
   * @return A list of GitHubRepositoryResponseDto objects representing the user's repositories.
   * @throws HeaderNotValidException If the provided header is not valid ("application/json" is expected).
   */
  @GetMapping(value = "/{username}")
  public List<GithubRepositoryResponseDto> getAllUserRepositories(@RequestHeader(HttpHeaders.ACCEPT) String header, @PathVariable String username) {
    log.info("GET-request: getting repositories for user with username: {}", username);

    if (!header.equalsIgnoreCase(ApiConstants.CORRECT_ACCEPT_HEADER)) {

      throw new HeaderNotValidException("You must use header Accept with value application/json");
    }

    return githubRepositoryService.getAllUserRepositories(username);
  }
}
