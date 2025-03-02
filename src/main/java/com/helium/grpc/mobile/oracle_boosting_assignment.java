// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf enum {@code helium.poc_mobile.oracle_boosting_assignment}
 */
public enum oracle_boosting_assignment
    implements com.google.protobuf.ProtocolMessageEnum {
  /**
   * <code>a = 0;</code>
   */
  a(0),
  /**
   * <code>b = 1;</code>
   */
  b(1),
  /**
   * <code>c = 2;</code>
   */
  c(2),
  UNRECOGNIZED(-1),
  ;

  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      oracle_boosting_assignment.class.getName());
  }
  /**
   * <code>a = 0;</code>
   */
  public static final int a_VALUE = 0;
  /**
   * <code>b = 1;</code>
   */
  public static final int b_VALUE = 1;
  /**
   * <code>c = 2;</code>
   */
  public static final int c_VALUE = 2;


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
  public static oracle_boosting_assignment valueOf(int value) {
    return forNumber(value);
  }

  /**
   * @param value The numeric wire value of the corresponding enum entry.
   * @return The enum associated with the given numeric wire value.
   */
  public static oracle_boosting_assignment forNumber(int value) {
    switch (value) {
      case 0: return a;
      case 1: return b;
      case 2: return c;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<oracle_boosting_assignment>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      oracle_boosting_assignment> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<oracle_boosting_assignment>() {
          public oracle_boosting_assignment findValueByNumber(int number) {
            return oracle_boosting_assignment.forNumber(number);
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
    return com.helium.grpc.mobile.PocMobile.getDescriptor().getEnumTypes().get(18);
  }

  private static final oracle_boosting_assignment[] VALUES = values();

  public static oracle_boosting_assignment valueOf(
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

  private oracle_boosting_assignment(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:helium.poc_mobile.oracle_boosting_assignment)
}

