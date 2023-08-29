package kwiatkowski.brajan.githubscrapper.service;

import java.util.List;
import kwiatkowski.brajan.githubscrapper.dto.response.GithubRepositoryResponseDto;

public interface GithubRepositoryService {

  List<GithubRepositoryResponseDto> getAllUserRepositories(String username);
}
