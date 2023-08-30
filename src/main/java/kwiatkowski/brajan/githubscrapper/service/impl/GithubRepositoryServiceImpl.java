package kwiatkowski.brajan.githubscrapper.service.impl;

import java.util.ArrayList;
import java.util.List;
import kwiatkowski.brajan.githubscrapper.converter.EntityConverter;
import kwiatkowski.brajan.githubscrapper.dto.request.BranchRequestDto;
import kwiatkowski.brajan.githubscrapper.dto.request.GithubRepositoryRequestDto;
import kwiatkowski.brajan.githubscrapper.dto.response.GithubRepositoryResponseDto;
import kwiatkowski.brajan.githubscrapper.exception.NotFoundException;
import kwiatkowski.brajan.githubscrapper.service.GithubRepositoryService;
import kwiatkowski.brajan.githubscrapper.util.ApiConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Service implementation for interacting with GitHub repositories.
 */
@Slf4j
@Service
public class GithubRepositoryServiceImpl implements GithubRepositoryService {

  private final EntityConverter entityConverter;
  private final WebClient githubApiClient;

  /**
   * Constructs a GithubRepositoryServiceImpl instance.
   *
   * @param entityConverter The converter for mapping entities to DTOs.
   * @param builder         The WebClient builder for making API requests.
   */
  public GithubRepositoryServiceImpl(EntityConverter entityConverter, WebClient.Builder builder) {
    this.entityConverter = entityConverter;
    this.githubApiClient = builder.baseUrl(ApiConstants.BASE_URL).build();
  }

  /**
   * Retrieves a list of non-fork GitHub repositories owned by a specific user.
   *
   * @param username The username of the GitHub user.
   * @return A list of GithubRepositoryResponseDto objects representing the user's repositories.
   */
  @Override
  public List<GithubRepositoryResponseDto> getAllUserRepositories(String username) {

    List<GithubRepositoryResponseDto> githubRepositories = new ArrayList<>();

    for (var repositoryRequestDto : fetchRepositories(username)) {
      if (!repositoryRequestDto.isFork()) {
        log.info("fetching repository branches for user with username: {}", username);
        List<BranchRequestDto> branches = fetchRepositoryBranches(repositoryRequestDto.getBranchesUrl());
        githubRepositories.add(
            entityConverter.mapToGithubRepositoryResponseDto(repositoryRequestDto, branches));
      }
    }

    return githubRepositories;
  }

  /**
   * Fetches the list of repositories for the given GitHub username from the GitHub API.
   *
   * @param username The username of the GitHub user.
   * @return A list of GithubRepositoryRequestDto objects representing the user's repositories.
   */
  private List<GithubRepositoryRequestDto> fetchRepositories(String username) {
    Object[] allUserRepositories = fetchInfoFromApi(createUriForUserRepositories(username));
    log.info("repositories fetched successfully for user with username: {}", username);

    return entityConverter.mapJsonResponseToGithubRepositoryRequestDto(allUserRepositories);
  }

  /**
   * Fetches the list of branches for a specific GitHub repository from the GitHub API.
   *
   * @param branchesUrl The URL to access the branches of the repository.
   * @return A list of BranchRequestDto objects representing the repository's branches.
   */
  private List<BranchRequestDto> fetchRepositoryBranches(String branchesUrl) {
    Object[] allBranchesInRepository = fetchInfoFromApi(createUriForRepositoryBranches(branchesUrl));

    return entityConverter.mapJsonResponseToBranchRequestDtoList(allBranchesInRepository);
  }

  /**
   * Creates the URI to fetch user repositories from the GitHub API.
   *
   * @param username The username of the GitHub user.
   * @return The URI for fetching user repositories.
   */
  private String createUriForUserRepositories(String username) {
    return "users/" + username + "/repos";
  }

  /**
   * Creates the URI to fetch branches of a repository from its branches URL.
   *
   * @param branchesUrl The URL to access the branches of the repository.
   * @return The URI for fetching repository branches.
   */
  private String createUriForRepositoryBranches(String branchesUrl) {
    String empty = "";
    String unusedValue = "{/branch}";
    return branchesUrl.replace(ApiConstants.BASE_URL, empty).replace(unusedValue, empty);
  }

  /**
   * Fetches JSON information from the GitHub API using the given URI.
   *
   * @param uri The URI to make the API request.
   * @return An array of JSON objects retrieved from the API response.
   */
  private Object[] fetchInfoFromApi(String uri) {
    Mono<Object[]>
        response =  githubApiClient.get().uri(uri)
        .accept(MediaType.APPLICATION_JSON).retrieve()
        .bodyToMono(Object[].class).onErrorResume(e -> Mono.error(new NotFoundException(uri))).log();

    return response.block();
  }
}
