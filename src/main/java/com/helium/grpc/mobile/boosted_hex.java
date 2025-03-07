// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf type {@code helium.poc_mobile.boosted_hex}
 */
public final class boosted_hex extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:helium.poc_mobile.boosted_hex)
    boosted_hexOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      boosted_hex.class.getName());
  }
  // Use boosted_hex.newBuilder() to construct.
  private boosted_hex(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private boosted_hex() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_boosted_hex_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_boosted_hex_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.mobile.boosted_hex.class, com.helium.grpc.mobile.boosted_hex.Builder.class);
  }

  public static final int LOCATION_FIELD_NUMBER = 1;
  private long location_ = 0L;
  /**
   * <pre>
   * The res12 h3 index of the boosted hex
   * </pre>
   *
   * <code>uint64 location = 1;</code>
   * @return The location.
   */
  @java.lang.Override
  public long getLocation() {
    return location_;
  }

  public static final int MULTIPLIER_FIELD_NUMBER = 2;
  private int multiplier_ = 0;
  /**
   * <pre>
   * the multiplier applied to this hex
   * </pre>
   *
   * <code>uint32 multiplier = 2;</code>
   * @return The multiplier.
   */
  @java.lang.Override
  public int getMultiplier() {
    return multiplier_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (location_ != 0L) {
      output.writeUInt64(1, location_);
    }
    if (multiplier_ != 0) {
      output.writeUInt32(2, multiplier_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (location_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, location_);
    }
    if (multiplier_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(2, multiplier_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.helium.grpc.mobile.boosted_hex)) {
      return super.equals(obj);
    }
    com.helium.grpc.mobile.boosted_hex other = (com.helium.grpc.mobile.boosted_hex) obj;

    if (getLocation()
        != other.getLocation()) return false;
    if (getMultiplier()
        != other.getMultiplier()) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + LOCATION_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getLocation());
    hash = (37 * hash) + MULTIPLIER_FIELD_NUMBER;
    hash = (53 * hash) + getMultiplier();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.mobile.boosted_hex parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.mobile.boosted_hex parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.mobile.boosted_hex parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.boosted_hex parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.helium.grpc.mobile.boosted_hex prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code helium.poc_mobile.boosted_hex}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_mobile.boosted_hex)
      com.helium.grpc.mobile.boosted_hexOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_boosted_hex_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_boosted_hex_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.mobile.boosted_hex.class, com.helium.grpc.mobile.boosted_hex.Builder.class);
    }

    // Construct using com.helium.grpc.mobile.boosted_hex.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      location_ = 0L;
      multiplier_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_boosted_hex_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.boosted_hex getDefaultInstanceForType() {
      return com.helium.grpc.mobile.boosted_hex.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.mobile.boosted_hex build() {
      com.helium.grpc.mobile.boosted_hex result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.boosted_hex buildPartial() {
      com.helium.grpc.mobile.boosted_hex result = new com.helium.grpc.mobile.boosted_hex(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.mobile.boosted_hex result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.location_ = location_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.multiplier_ = multiplier_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.mobile.boosted_hex) {
        return mergeFrom((com.helium.grpc.mobile.boosted_hex)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.mobile.boosted_hex other) {
      if (other == com.helium.grpc.mobile.boosted_hex.getDefaultInstance()) return this;
      if (other.getLocation() != 0L) {
        setLocation(other.getLocation());
      }
      if (other.getMultiplier() != 0) {
        setMultiplier(other.getMultiplier());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              location_ = input.readUInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              multiplier_ = input.readUInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private long location_ ;
    /**
     * <pre>
     * The res12 h3 index of the boosted hex
     * </pre>
     *
     * <code>uint64 location = 1;</code>
     * @return The location.
     */
    @java.lang.Override
    public long getLocation() {
      return location_;
    }
    /**
     * <pre>
     * The res12 h3 index of the boosted hex
     * </pre>
     *
     * <code>uint64 location = 1;</code>
     * @param value The location to set.
     * @return This builder for chaining.
     */
    public Builder setLocation(long value) {

      location_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The res12 h3 index of the boosted hex
     * </pre>
     *
     * <code>uint64 location = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearLocation() {
      bitField0_ = (bitField0_ & ~0x00000001);
      location_ = 0L;
      onChanged();
      return this;
    }

    private int multiplier_ ;
    /**
     * <pre>
     * the multiplier applied to this hex
     * </pre>
     *
     * <code>uint32 multiplier = 2;</code>
     * @return The multiplier.
     */
    @java.lang.Override
    public int getMultiplier() {
      return multiplier_;
    }
    /**
     * <pre>
     * the multiplier applied to this hex
     * </pre>
     *
     * <code>uint32 multiplier = 2;</code>
     * @param value The multiplier to set.
     * @return This builder for chaining.
     */
    public Builder setMultiplier(int value) {

      multiplier_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * the multiplier applied to this hex
     * </pre>
     *
     * <code>uint32 multiplier = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearMultiplier() {
      bitField0_ = (bitField0_ & ~0x00000002);
      multiplier_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helium.poc_mobile.boosted_hex)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_mobile.boosted_hex)
  private static final com.helium.grpc.mobile.boosted_hex DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.mobile.boosted_hex();
  }

  public static com.helium.grpc.mobile.boosted_hex getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<boosted_hex>
      PARSER = new com.google.protobuf.AbstractParser<boosted_hex>() {
    @java.lang.Override
    public boosted_hex parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<boosted_hex> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<boosted_hex> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.mobile.boosted_hex getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

