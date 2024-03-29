// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_mobile.proto

// Protobuf Java Version: 3.25.1
package com.helium.grpc.mobile;

/**
 * Protobuf type {@code helium.poc_mobile.subscriber_reward}
 */
public final class subscriber_reward extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:helium.poc_mobile.subscriber_reward)
    subscriber_rewardOrBuilder {
private static final long serialVersionUID = 0L;
  // Use subscriber_reward.newBuilder() to construct.
  private subscriber_reward(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private subscriber_reward() {
    subscriberId_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new subscriber_reward();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_subscriber_reward_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_subscriber_reward_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.mobile.subscriber_reward.class, com.helium.grpc.mobile.subscriber_reward.Builder.class);
  }

  public static final int SUBSCRIBER_ID_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString subscriberId_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <pre>
   * id of the subscriber to which the reward will be credited
   * </pre>
   *
   * <code>bytes subscriber_id = 1;</code>
   * @return The subscriberId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getSubscriberId() {
    return subscriberId_;
  }

  public static final int DISCOVERY_LOCATION_AMOUNT_FIELD_NUMBER = 2;
  private long discoveryLocationAmount_ = 0L;
  /**
   * <pre>
   *&#47; Amount in bones credited to the subscriber for location sharing
   * </pre>
   *
   * <code>uint64 discovery_location_amount = 2;</code>
   * @return The discoveryLocationAmount.
   */
  @java.lang.Override
  public long getDiscoveryLocationAmount() {
    return discoveryLocationAmount_;
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
    if (!subscriberId_.isEmpty()) {
      output.writeBytes(1, subscriberId_);
    }
    if (discoveryLocationAmount_ != 0L) {
      output.writeUInt64(2, discoveryLocationAmount_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!subscriberId_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, subscriberId_);
    }
    if (discoveryLocationAmount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, discoveryLocationAmount_);
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
    if (!(obj instanceof com.helium.grpc.mobile.subscriber_reward)) {
      return super.equals(obj);
    }
    com.helium.grpc.mobile.subscriber_reward other = (com.helium.grpc.mobile.subscriber_reward) obj;

    if (!getSubscriberId()
        .equals(other.getSubscriberId())) return false;
    if (getDiscoveryLocationAmount()
        != other.getDiscoveryLocationAmount()) return false;
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
    hash = (37 * hash) + SUBSCRIBER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getSubscriberId().hashCode();
    hash = (37 * hash) + DISCOVERY_LOCATION_AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDiscoveryLocationAmount());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.mobile.subscriber_reward parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.mobile.subscriber_reward parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.mobile.subscriber_reward parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.subscriber_reward parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.helium.grpc.mobile.subscriber_reward prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code helium.poc_mobile.subscriber_reward}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_mobile.subscriber_reward)
      com.helium.grpc.mobile.subscriber_rewardOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_subscriber_reward_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_subscriber_reward_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.mobile.subscriber_reward.class, com.helium.grpc.mobile.subscriber_reward.Builder.class);
    }

    // Construct using com.helium.grpc.mobile.subscriber_reward.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      subscriberId_ = com.google.protobuf.ByteString.EMPTY;
      discoveryLocationAmount_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_subscriber_reward_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.subscriber_reward getDefaultInstanceForType() {
      return com.helium.grpc.mobile.subscriber_reward.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.mobile.subscriber_reward build() {
      com.helium.grpc.mobile.subscriber_reward result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.subscriber_reward buildPartial() {
      com.helium.grpc.mobile.subscriber_reward result = new com.helium.grpc.mobile.subscriber_reward(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.mobile.subscriber_reward result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.subscriberId_ = subscriberId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.discoveryLocationAmount_ = discoveryLocationAmount_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.mobile.subscriber_reward) {
        return mergeFrom((com.helium.grpc.mobile.subscriber_reward)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.mobile.subscriber_reward other) {
      if (other == com.helium.grpc.mobile.subscriber_reward.getDefaultInstance()) return this;
      if (other.getSubscriberId() != com.google.protobuf.ByteString.EMPTY) {
        setSubscriberId(other.getSubscriberId());
      }
      if (other.getDiscoveryLocationAmount() != 0L) {
        setDiscoveryLocationAmount(other.getDiscoveryLocationAmount());
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
            case 10: {
              subscriberId_ = input.readBytes();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 16: {
              discoveryLocationAmount_ = input.readUInt64();
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

    private com.google.protobuf.ByteString subscriberId_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * id of the subscriber to which the reward will be credited
     * </pre>
     *
     * <code>bytes subscriber_id = 1;</code>
     * @return The subscriberId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSubscriberId() {
      return subscriberId_;
    }
    /**
     * <pre>
     * id of the subscriber to which the reward will be credited
     * </pre>
     *
     * <code>bytes subscriber_id = 1;</code>
     * @param value The subscriberId to set.
     * @return This builder for chaining.
     */
    public Builder setSubscriberId(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      subscriberId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * id of the subscriber to which the reward will be credited
     * </pre>
     *
     * <code>bytes subscriber_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSubscriberId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      subscriberId_ = getDefaultInstance().getSubscriberId();
      onChanged();
      return this;
    }

    private long discoveryLocationAmount_ ;
    /**
     * <pre>
     *&#47; Amount in bones credited to the subscriber for location sharing
     * </pre>
     *
     * <code>uint64 discovery_location_amount = 2;</code>
     * @return The discoveryLocationAmount.
     */
    @java.lang.Override
    public long getDiscoveryLocationAmount() {
      return discoveryLocationAmount_;
    }
    /**
     * <pre>
     *&#47; Amount in bones credited to the subscriber for location sharing
     * </pre>
     *
     * <code>uint64 discovery_location_amount = 2;</code>
     * @param value The discoveryLocationAmount to set.
     * @return This builder for chaining.
     */
    public Builder setDiscoveryLocationAmount(long value) {

      discoveryLocationAmount_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *&#47; Amount in bones credited to the subscriber for location sharing
     * </pre>
     *
     * <code>uint64 discovery_location_amount = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearDiscoveryLocationAmount() {
      bitField0_ = (bitField0_ & ~0x00000002);
      discoveryLocationAmount_ = 0L;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:helium.poc_mobile.subscriber_reward)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_mobile.subscriber_reward)
  private static final com.helium.grpc.mobile.subscriber_reward DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.mobile.subscriber_reward();
  }

  public static com.helium.grpc.mobile.subscriber_reward getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<subscriber_reward>
      PARSER = new com.google.protobuf.AbstractParser<subscriber_reward>() {
    @java.lang.Override
    public subscriber_reward parsePartialFrom(
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

  public static com.google.protobuf.Parser<subscriber_reward> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<subscriber_reward> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.mobile.subscriber_reward getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

