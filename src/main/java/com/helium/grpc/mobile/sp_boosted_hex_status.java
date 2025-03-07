// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf enum {@code helium.poc_mobile.sp_boosted_hex_status}
 */
public enum sp_boosted_hex_status
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>sp_boosted_hex_status_eligible = 0;</code>
   */
  sp_boosted_hex_status_eligible(0),
  /**
   * <code>sp_boosted_hex_status_location_score_below_threshold = 1;</code>
   */
  sp_boosted_hex_status_location_score_below_threshold(1),
  /**
   * <code>sp_boosted_hex_status_radio_threshold_not_met = 2;</code>
   */
  sp_boosted_hex_status_radio_threshold_not_met(2),
  /**
   * <code>sp_boosted_hex_status_service_provider_ban = 3 [deprecated = true];</code>
   */
  @java.lang.Deprecated
  sp_boosted_hex_status_service_provider_ban(3),
  /**
   * <code>sp_boosted_hex_status_average_asserted_distance_over_limit = 4;</code>
   */
  sp_boosted_hex_status_average_asserted_distance_over_limit(4),
  /**
   * <code>sp_boosted_hex_status_not_enough_connections = 5;</code>
   */
  sp_boosted_hex_status_not_enough_connections(5),
  UNRECOGNIZED(-1),
  ;

  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      sp_boosted_hex_status.class.getName());
  }
  /**
   * <code>sp_boosted_hex_status_eligible = 0;</code>
   */
  public static final int sp_boosted_hex_status_eligible_VALUE = 0;
  /**
   * <code>sp_boosted_hex_status_location_score_below_threshold = 1;</code>
   */
  public static final int sp_boosted_hex_status_location_score_below_threshold_VALUE = 1;
  /**
   * <code>sp_boosted_hex_status_radio_threshold_not_met = 2;</code>
   */
  public static final int sp_boosted_hex_status_radio_threshold_not_met_VALUE = 2;
  /**
   * <code>sp_boosted_hex_status_service_provider_ban = 3 [deprecated = true];</code>
   */
  @java.lang.Deprecated public static final int sp_boosted_hex_status_service_provider_ban_VALUE = 3;
  /**
   * <code>sp_boosted_hex_status_average_asserted_distance_over_limit = 4;</code>
   */
  public static final int sp_boosted_hex_status_average_asserted_distance_over_limit_VALUE = 4;
  /**
   * <code>sp_boosted_hex_status_not_enough_connections = 5;</code>
   */
  public static final int sp_boosted_hex_status_not_enough_connections_VALUE = 5;


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
  public static sp_boosted_hex_status valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static sp_boosted_hex_status forNumber(int value) {
    switch (value) {
      case 0: return sp_boosted_hex_status_eligible;
      case 1: return sp_boosted_hex_status_location_score_below_threshold;
      case 2: return sp_boosted_hex_status_radio_threshold_not_met;
      case 3: return sp_boosted_hex_status_service_provider_ban;
      case 4: return sp_boosted_hex_status_average_asserted_distance_over_limit;
      case 5: return sp_boosted_hex_status_not_enough_connections;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<sp_boosted_hex_status>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      sp_boosted_hex_status> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<sp_boosted_hex_status>() {
          public sp_boosted_hex_status findValueByNumber(int number) {
            return sp_boosted_hex_status.forNumber(number);
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
    return com.helium.grpc.mobile.PocMobile.getDescriptor().getEnumTypes().get(11);
  }

  private static final sp_boosted_hex_status[] VALUES = values();

  public static sp_boosted_hex_status valueOf(
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

  private sp_boosted_hex_status(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:helium.poc_mobile.sp_boosted_hex_status)
}

