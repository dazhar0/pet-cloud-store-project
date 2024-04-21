package org.openapitools.model;


import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openapitools.jackson.nullable.JsonNullable;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CustomPet
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-14T23:19:48.387678-04:00[America/New_York]")

public class CustomPet   {
  @JsonProperty("customId")
  private Long customId;

  @JsonProperty("customName")
  private String customName;

  @JsonProperty("customTag")
  private String customTag;

  public CustomPet customId(Long customId) {
    this.customId = customId;
    return this;
  }

  /**
   * Get customId
   * @return customId
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getCustomId() {
    return customId;
  }

  public void setCustomId(Long customId) {
    this.customId = customId;
  }

  public CustomPet customName(String customName) {
    this.customName = customName;
    return this;
  }

  /**
   * Get customName
   * @return customName
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCustomName() {
    return customName;
  }

  public void setCustomName(String customName) {
    this.customName = customName;
  }

  public CustomPet customTag(String customTag) {
    this.customTag = customTag;
    return this;
  }

  /**
   * Get customTag
   * @return customTag
  */
  @ApiModelProperty(value = "")


  public String getCustomTag() {
    return customTag;
  }

  public void setCustomTag(String customTag) {
    this.customTag = customTag;
  }
}

