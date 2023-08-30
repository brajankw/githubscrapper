package kwiatkowski.brajan.githubscrapper.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import kwiatkowski.brajan.githubscrapper.dto.request.BranchRequestDto;
import kwiatkowski.brajan.githubscrapper.dto.response.BranchResponseDto;
import kwiatkowski.brajan.githubscrapper.dto.response.GithubRepositoryResponseDto;
import kwiatkowski.brajan.githubscrapper.dto.request.GithubRepositoryRequestDto;
import org.springframework.stereotype.Component;

/**
 * This component class provides methods for converting between different DTOs and entities.
 */
@Component
public class EntityConverter {

  private final ObjectMapper mapper;

  /**
   * Constructs an EntityConverter instance.
   */
  public EntityConverter() {
    mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * Converts a JSON response object to a BranchRequestDto object.
   *
   * @param object The JSON response object.
   * @return The corresponding BranchRequestDto object.
   */
  public BranchRequestDto mapJsonResponseToBranchRequestDto(Object object) {

    return mapper.convertValue(object, BranchRequestDto.class);
  }

  /**
   * Converts a JSON response object to a GithubRepositoryRequestDto object.
   *
   * @param object The JSON response object.
   * @return The corresponding GithubRepositoryRequestDto object.
   */
  public GithubRepositoryRequestDto mapJsonResponseToGithubRepositoryRequestDto(Object object) {

    return mapper.convertValue(object, GithubRepositoryRequestDto.class);
  }

  /**
   * Converts an array of JSON response objects to a list of GithubRepositoryRequestDto objects.
   *
   * @param objects The array of JSON response objects.
   * @return The corresponding list of GithubRepositoryRequestDto objects.
   */
  public List<GithubRepositoryRequestDto> mapJsonResponseToGithubRepositoryRequestDto(
      Object[] objects) {

    return Arrays.stream(objects)
        .map(this::mapJsonResponseToGithubRepositoryRequestDto)
        .toList();

  }

  /**
   * Converts an array of JSON response objects to a list of BranchRequestDto objects.
   *
   * @param objects The array of JSON response objects.
   * @return The corresponding list of BranchRequestDto objects.
   */
  public List<BranchRequestDto> mapJsonResponseToBranchRequestDtoList(Object[] objects) {

    return Arrays.stream(objects)
        .map(this::mapJsonResponseToBranchRequestDto)
        .toList();

  }

  /**
   * Maps a GithubRepositoryRequestDto and a list of BranchRequestDto objects to a GithubRepositoryResponseDto object.
   *
   * @param githubRepositoryRequestDto The GithubRepositoryRequestDto object.
   * @param branchRequestDtos          The list of BranchRequestDto objects.
   * @return The corresponding GithubRepositoryResponseDto object.
   */
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

  /**
   * Converts a BranchRequestDto object to a BranchResponseDto object.
   *
   * @param branchRequestDto The BranchRequestDto object.
   * @return The corresponding BranchResponseDto object.
   */

  public BranchResponseDto mapBranchRequestToBranchResponseDto(BranchRequestDto branchRequestDto) {

    return BranchResponseDto.builder()
        .name(branchRequestDto.getName())
        .sha(branchRequestDto.getCommit().getSha()).build();
  }
}