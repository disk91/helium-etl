// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf enum {@code helium.poc_mobile.invalidated_radio_threshold_report_verification_status}
 */
public enum invalidated_radio_threshold_report_verification_status
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>invalidated_threshold_report_status_valid = 0;</code>
   */
  invalidated_threshold_report_status_valid(0),
  /**
   * <code>invalidated_threshold_report_status_invalid_carrier_key = 1;</code>
   */
  invalidated_threshold_report_status_invalid_carrier_key(1),
  UNRECOGNIZED(-1),
  ;

  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      invalidated_radio_threshold_report_verification_status.class.getName());
  }
  /**
   * <code>invalidated_threshold_report_status_valid = 0;</code>
   */
  public static final int invalidated_threshold_report_status_valid_VALUE = 0;
  /**
   * <code>invalidated_threshold_report_status_invalid_carrier_key = 1;</code>
   */
  public static final int invalidated_threshold_report_status_invalid_carrier_key_VALUE = 1;


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
  public static invalidated_radio_threshold_report_verification_status valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static invalidated_radio_threshold_report_verification_status forNumber(int value) {
    switch (value) {
      case 0: return invalidated_threshold_report_status_valid;
      case 1: return invalidated_threshold_report_status_invalid_carrier_key;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<invalidated_radio_threshold_report_verification_status>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      invalidated_radio_threshold_report_verification_status> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<invalidated_radio_threshold_report_verification_status>() {
          public invalidated_radio_threshold_report_verification_status findValueByNumber(int number) {
            return invalidated_radio_threshold_report_verification_status.forNumber(number);
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
    return com.helium.grpc.mobile.PocMobile.getDescriptor().getEnumTypes().get(4);
  }

  private static final invalidated_radio_threshold_report_verification_status[] VALUES = values();

  public static invalidated_radio_threshold_report_verification_status valueOf(
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

  private invalidated_radio_threshold_report_verification_status(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:helium.poc_mobile.invalidated_radio_threshold_report_verification_status)
}

