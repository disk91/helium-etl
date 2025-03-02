// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf enum {@code helium.poc_mobile.speedtest_verification_result}
 */
public enum speedtest_verification_result
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>speedtest_valid = 0;</code>
   */
  speedtest_valid(0),
  /**
   * <code>speedtest_gateway_not_found = 1;</code>
   */
  speedtest_gateway_not_found(1),
  /**
   * <pre>
   * Device Type not expected to submit speedtests
   * </pre>
   *
   * <code>speedtest_invalid_device_type = 2;</code>
   */
  speedtest_invalid_device_type(2),
  UNRECOGNIZED(-1),
  ;

  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      speedtest_verification_result.class.getName());
  }
  /**
   * <code>speedtest_valid = 0;</code>
   */
  public static final int speedtest_valid_VALUE = 0;
  /**
   * <code>speedtest_gateway_not_found = 1;</code>
   */
  public static final int speedtest_gateway_not_found_VALUE = 1;
  /**
   * <pre>
   * Device Type not expected to submit speedtests
   * </pre>
   *
   * <code>speedtest_invalid_device_type = 2;</code>
   */
  public static final int speedtest_invalid_device_type_VALUE = 2;


  public final int getNumber() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalArgumentException(
          "Can't get the number of an unknown enum value.");
    }
    return value;
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static speedtest_verification_result valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static speedtest_verification_result forNumber(int value) {
    switch (value) {
      case 0: return speedtest_valid;
      case 1: return speedtest_gateway_not_found;
      case 2: return speedtest_invalid_device_type;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<speedtest_verification_result>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      speedtest_verification_result> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<speedtest_verification_result>() {
          public speedtest_verification_result findValueByNumber(int number) {
            return speedtest_verification_result.forNumber(number);
          }
        };

  public final com.google.protobuf.Descriptors.EnumValueDescriptor
      getValueDescriptor() {
    if (this == UNRECOGNIZED) {
      throw new java.lang.IllegalStateException(
          "Can't get the descriptor of an unrecognized enum value.");
    }
    return getDescriptor().getValues().get(ordinal());
  }
  public final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }
  public static final com.google.protobuf.Descriptors.EnumDescriptor
      getDescriptor() {
    return com.helium.grpc.mobile.PocMobile.getDescriptor().getEnumTypes().get(0);
  }

  private static final speedtest_verification_result[] VALUES = values();

  public static speedtest_verification_result valueOf(
      com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
    if (desc.getType() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "EnumValueDescriptor is not for this type.");
    }
    if (desc.getIndex() == -1) {
      return UNRECOGNIZED;
    }
    return VALUES[desc.getIndex()];
  }

  private final int value;

  private speedtest_verification_result(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:helium.poc_mobile.speedtest_verification_result)
}

