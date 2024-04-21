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
 * CustomError
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2020-05-14T23:19:48.387678-04:00[America/New_York]")

public class CustomError   {
  @JsonProperty("customCode")
  private Integer customCode;

  @JsonProperty("customMessage")
  private String customMessage;

  public CustomError customCode(Integer customCode) {
    this.customCode = customCode;
    return this;
  }

  /**
   * Get customCode
   * @return customCode
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Integer getCustomCode() {
    return customCode;
  }

  public void setCustomCode(Integer customCode) {
    this.customCode = customCode;
  }

  public CustomError customMessage(String customMessage) {
    this.customMessage = customMessage;
    return this;
  }

  /**
   * Get customMessage
   * @return customMessage
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCustomMessage() {
    return customMessage;
  }

  public void setCustomMessage(String customMessage) {
    this.customMessage = customMessage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomError customError = (CustomError) o;
    return Objects.equals(this.customCode, customError.customCode) &&
        Objects.equals(this.customMessage, customError.customMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customCode, customMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomError {\n");
    
    sb.append("    customCode: ").append(toIndentedString(customCode)).append("\n");
    sb.append("    customMessage: ").append(toIndentedString(customMessage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
