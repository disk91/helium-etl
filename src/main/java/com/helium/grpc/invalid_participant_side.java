// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

/**
 * Protobuf enum {@code helium.poc_lora.invalid_participant_side}
 */
public enum invalid_participant_side
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>side_none = 0;</code>
   */
  side_none(0),
  /**
   * <code>beaconer = 2;</code>
   */
  beaconer(2),
  /**
   * <code>witness = 3;</code>
   */
  witness(3),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>side_none = 0;</code>
   */
  public static final int side_none_VALUE = 0;
  /**
   * <code>beaconer = 2;</code>
   */
  public static final int beaconer_VALUE = 2;
  /**
   * <code>witness = 3;</code>
   */
  public static final int witness_VALUE = 3;


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
  public static invalid_participant_side valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static invalid_participant_side forNumber(int value) {
    switch (value) {
      case 0: return side_none;
      case 2: return beaconer;
      case 3: return witness;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<invalid_participant_side>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      invalid_participant_side> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<invalid_participant_side>() {
          public invalid_participant_side findValueByNumber(int number) {
            return invalid_participant_side.forNumber(number);
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
    return com.helium.grpc.PocLoRa.getDescriptor().getEnumTypes().get(1);
  }

  private static final invalid_participant_side[] VALUES = values();

  public static invalid_participant_side valueOf(
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

  private invalid_participant_side(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:helium.poc_lora.invalid_participant_side)
}

