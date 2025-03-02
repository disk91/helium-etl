// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf enum {@code helium.poc_mobile.unallocated_reward_type}
 */
public enum unallocated_reward_type
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>unallocated_reward_type_poc = 0;</code>
   */
  unallocated_reward_type_poc(0),
  /**
   * <code>unallocated_reward_type_discovery_location = 1;</code>
   */
  unallocated_reward_type_discovery_location(1),
  /**
   * <code>unallocated_reward_type_mapper = 2;</code>
   */
  unallocated_reward_type_mapper(2),
  /**
   * <code>unallocated_reward_type_service_provider = 3;</code>
   */
  unallocated_reward_type_service_provider(3),
  /**
   * <code>unallocated_reward_type_oracle = 4;</code>
   */
  unallocated_reward_type_oracle(4),
  /**
   * <code>unallocated_reward_type_data = 5;</code>
   */
  unallocated_reward_type_data(5),
  UNRECOGNIZED(-1),
  ;

  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      unallocated_reward_type.class.getName());
  }
  /**
   * <code>unallocated_reward_type_poc = 0;</code>
   */
  public static final int unallocated_reward_type_poc_VALUE = 0;
  /**
   * <code>unallocated_reward_type_discovery_location = 1;</code>
   */
  public static final int unallocated_reward_type_discovery_location_VALUE = 1;
  /**
   * <code>unallocated_reward_type_mapper = 2;</code>
   */
  public static final int unallocated_reward_type_mapper_VALUE = 2;
  /**
   * <code>unallocated_reward_type_service_provider = 3;</code>
   */
  public static final int unallocated_reward_type_service_provider_VALUE = 3;
  /**
   * <code>unallocated_reward_type_oracle = 4;</code>
   */
  public static final int unallocated_reward_type_oracle_VALUE = 4;
  /**
   * <code>unallocated_reward_type_data = 5;</code>
   */
  public static final int unallocated_reward_type_data_VALUE = 5;


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
  public static unallocated_reward_type valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static unallocated_reward_type forNumber(int value) {
    switch (value) {
      case 0: return unallocated_reward_type_poc;
      case 1: return unallocated_reward_type_discovery_location;
      case 2: return unallocated_reward_type_mapper;
      case 3: return unallocated_reward_type_service_provider;
      case 4: return unallocated_reward_type_oracle;
      case 5: return unallocated_reward_type_data;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<unallocated_reward_type>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      unallocated_reward_type> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<unallocated_reward_type>() {
          public unallocated_reward_type findValueByNumber(int number) {
            return unallocated_reward_type.forNumber(number);
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
    return com.helium.grpc.mobile.PocMobile.getDescriptor().getEnumTypes().get(13);
  }

  private static final unallocated_reward_type[] VALUES = values();

  public static unallocated_reward_type valueOf(
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

  private unallocated_reward_type(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:helium.poc_mobile.unallocated_reward_type)
}

