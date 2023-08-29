package kwiatkowski.brajan.githubscrapper.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import kwiatkowski.brajan.githubscrapper.dto.request.BranchRequestDto;
import kwiatkowski.brajan.githubscrapper.dto.response.BranchResponseDto;
import kwiatkowski.brajan.githubscrapper.dto.request.GithubRepositoryRequestDto;
import kwiatkowski.brajan.githubscrapper.dto.response.GithubRepositoryResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {

  private ObjectMapper mapper;

  public EntityConverter() {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public BranchRequestDto mapJsonResponseToBranchRequestDto(Object object) {

    return mapper.convertValue(object, BranchRequestDto.class);
  }

  public GithubRepositoryRequestDto mapJsonResponseToGithubRepositoryRequestDto(Object object) {

    return mapper.convertValue(object, GithubRepositoryRequestDto.class);
  }

  public List<GithubRepositoryRequestDto> mapJsonResponseToGithubRepositoryRequestDto(
      Object[] objects) {

    return Arrays.stream(objects)
        .map(this::mapJsonResponseToGithubRepositoryRequestDto)
        .toList();

  }

  public List<BranchRequestDto> mapJsonResponseToBranchRequestDtoList(Object[] objects) {

    return Arrays.stream(objects)
        .map(this::mapJsonResponseToBranchRequestDto)
        .toList();

  }

  public GithubRepositoryResponseDto mapToGithubRepositoryResponseDto(
      GithubRepositoryRequestDto githubRepositoryRequestDto,
      List<BranchRequestDto> branchRequestDtos) {

    return GithubRepositoryResponseDto.builder()
        .owner(githubRepositoryRequestDto.getOwner().getLogin())
        .name(githubRepositoryRequestDto.getName())
        .branches(
            branchRequestDtos
                .stream().map(this::mapBranchRequestToBranchResponseDto).toList())
        .build();
  }

  public BranchResponseDto mapBranchRequestToBranchResponseDto(BranchRequestDto branchRequestDto) {

    return BranchResponseDto.builder()
        .name(branchRequestDto.getName())
        .sha(branchRequestDto.getCommit().getSha()).build();
  }
}