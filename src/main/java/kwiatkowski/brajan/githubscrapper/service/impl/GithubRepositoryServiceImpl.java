package kwiatkowski.brajan.githubscrapper.service.impl;

import java.util.ArrayList;
import java.util.List;
import kwiatkowski.brajan.githubscrapper.converter.EntityConverter;
import kwiatkowski.brajan.githubscrapper.dto.request.BranchRequestDto;
import kwiatkowski.brajan.githubscrapper.dto.request.GithubRepositoryRequestDto;
import kwiatkowski.brajan.githubscrapper.dto.response.GithubRepositoryResponseDto;
import kwiatkowski.brajan.githubscrapper.exception.NotFoundException;
import kwiatkowski.brajan.githubscrapper.service.GithubRepositoryService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GithubRepositoryServiceImpl implements GithubRepositoryService {

  private final EntityConverter entityConverter;
  private final WebClient githubApiClient;
  private final String baseUrl = "https://api.github.com/";

  public GithubRepositoryServiceImpl(EntityConverter entityConverter, WebClient.Builder builder) {
    this.entityConverter = entityConverter;
    this.githubApiClient = builder.baseUrl(baseUrl).build();
  }

  @Override
  public List<GithubRepositoryResponseDto> getAllUserRepositories(String username) {

    List<GithubRepositoryResponseDto> githubRepositories = new ArrayList<>();;

    for (var repositoryRequestDto : fetchRepositories(username)) {
      if (!repositoryRequestDto.isFork()) {
        List<BranchRequestDto> branches = fetchRepositoryBranches(repositoryRequestDto.getBranchesUrl());
        githubRepositories.add(
            entityConverter.mapToGithubRepositoryResponseDto(repositoryRequestDto, branches));
      }
    }

    return githubRepositories;
  }

  private List<GithubRepositoryRequestDto> fetchRepositories(String username) {
    Object[] allUserRepositories = fetchInfoFromApi(createUriForUserRepositories(username));

    return entityConverter.mapJsonResponseToGithubRepositoryRequestDto(allUserRepositories);
  }

  private List<BranchRequestDto> fetchRepositoryBranches(String branchesUrl) {
    Object[] allBranchesInRepository = fetchInfoFromApi(createUriForRepositoryBranches(branchesUrl));

    return entityConverter.mapJsonResponseToBranchRequestDtoList(allBranchesInRepository);
  }


  private String createUriForUserRepositories(String username) {
    return "users/" +username + "/repos";
  }

  private String createUriForRepositoryBranches(String branchesUrl) {
    String empty = "";
    String unusedValue = "{/branch}";
    return branchesUrl.replace(baseUrl, empty).replace(unusedValue, empty);
  }

  private Object[] fetchInfoFromApi(String uri) {
    Mono<Object[]>
        response =  githubApiClient.get().uri(uri)
        .accept(MediaType.APPLICATION_JSON).retrieve()
        .bodyToMono(Object[].class).onErrorResume(e -> Mono.error(new NotFoundException(uri))).log();

    return response.block();
  }
}
