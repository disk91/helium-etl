// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_lora.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc;

/**
 * Protobuf type {@code helium.poc_lora.gateway_reward}
 */
public final class gateway_reward extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:helium.poc_lora.gateway_reward)
    gateway_rewardOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      gateway_reward.class.getName());
  }
  // Use gateway_reward.newBuilder() to construct.
  private gateway_reward(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private gateway_reward() {
    hotspotKey_ = com.google.protobuf.ByteString.EMPTY;
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_gateway_reward_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_gateway_reward_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.gateway_reward.class, com.helium.grpc.gateway_reward.Builder.class);
  }

  public static final int HOTSPOT_KEY_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString hotspotKey_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <pre>
   * &#47; Public key of the hotspot
   * </pre>
   *
   * <code>bytes hotspot_key = 1;</code>
   * @return The hotspotKey.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getHotspotKey() {
    return hotspotKey_;
  }

  public static final int BEACON_AMOUNT_FIELD_NUMBER = 2;
  private long beaconAmount_ = 0L;
  /**
   * <pre>
   * &#47; Amount in iot bones credited to the hotspot for beaconing
   * </pre>
   *
   * <code>uint64 beacon_amount = 2;</code>
   * @return The beaconAmount.
   */
  @java.lang.Override
  public long getBeaconAmount() {
    return beaconAmount_;
  }

  public static final int WITNESS_AMOUNT_FIELD_NUMBER = 3;
  private long witnessAmount_ = 0L;
  /**
   * <pre>
   * &#47; Amount in iot bones credited to the hotspot for witnessing
   * </pre>
   *
   * <code>uint64 witness_amount = 3;</code>
   * @return The witnessAmount.
   */
  @java.lang.Override
  public long getWitnessAmount() {
    return witnessAmount_;
  }

  public static final int DC_TRANSFER_AMOUNT_FIELD_NUMBER = 4;
  private long dcTransferAmount_ = 0L;
  /**
   * <pre>
   * &#47; Amount in iot bones credited to the hotspot for data transfer
   * </pre>
   *
   * <code>uint64 dc_transfer_amount = 4;</code>
   * @return The dcTransferAmount.
   */
  @java.lang.Override
  public long getDcTransferAmount() {
    return dcTransferAmount_;
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
    if (!hotspotKey_.isEmpty()) {
      output.writeBytes(1, hotspotKey_);
    }
    if (beaconAmount_ != 0L) {
      output.writeUInt64(2, beaconAmount_);
    }
    if (witnessAmount_ != 0L) {
      output.writeUInt64(3, witnessAmount_);
    }
    if (dcTransferAmount_ != 0L) {
      output.writeUInt64(4, dcTransferAmount_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!hotspotKey_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, hotspotKey_);
    }
    if (beaconAmount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, beaconAmount_);
    }
    if (witnessAmount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, witnessAmount_);
    }
    if (dcTransferAmount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, dcTransferAmount_);
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
    if (!(obj instanceof com.helium.grpc.gateway_reward)) {
      return super.equals(obj);
    }
    com.helium.grpc.gateway_reward other = (com.helium.grpc.gateway_reward) obj;

    if (!getHotspotKey()
        .equals(other.getHotspotKey())) return false;
    if (getBeaconAmount()
        != other.getBeaconAmount()) return false;
    if (getWitnessAmount()
        != other.getWitnessAmount()) return false;
    if (getDcTransferAmount()
        != other.getDcTransferAmount()) return false;
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
    hash = (37 * hash) + HOTSPOT_KEY_FIELD_NUMBER;
    hash = (53 * hash) + getHotspotKey().hashCode();
    hash = (37 * hash) + BEACON_AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getBeaconAmount());
    hash = (37 * hash) + WITNESS_AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getWitnessAmount());
    hash = (37 * hash) + DC_TRANSFER_AMOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDcTransferAmount());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.gateway_reward parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.gateway_reward parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.gateway_reward parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.gateway_reward parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.gateway_reward parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.gateway_reward parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.gateway_reward parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.gateway_reward parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.gateway_reward parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.gateway_reward parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.gateway_reward parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.gateway_reward parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.gateway_reward prototype) {
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
   * Protobuf type {@code helium.poc_lora.gateway_reward}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_lora.gateway_reward)
      com.helium.grpc.gateway_rewardOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_gateway_reward_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_gateway_reward_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.gateway_reward.class, com.helium.grpc.gateway_reward.Builder.class);
    }

    // Construct using com.helium.grpc.gateway_reward.newBuilder()
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
      hotspotKey_ = com.google.protobuf.ByteString.EMPTY;
      beaconAmount_ = 0L;
      witnessAmount_ = 0L;
      dcTransferAmount_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_gateway_reward_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.gateway_reward getDefaultInstanceForType() {
      return com.helium.grpc.gateway_reward.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.gateway_reward build() {
      com.helium.grpc.gateway_reward result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.gateway_reward buildPartial() {
      com.helium.grpc.gateway_reward result = new com.helium.grpc.gateway_reward(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.gateway_reward result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.hotspotKey_ = hotspotKey_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.beaconAmount_ = beaconAmount_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.witnessAmount_ = witnessAmount_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.dcTransferAmount_ = dcTransferAmount_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.gateway_reward) {
        return mergeFrom((com.helium.grpc.gateway_reward)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.gateway_reward other) {
      if (other == com.helium.grpc.gateway_reward.getDefaultInstance()) return this;
      if (other.getHotspotKey() != com.google.protobuf.ByteString.EMPTY) {
        setHotspotKey(other.getHotspotKey());
      }
      if (other.getBeaconAmount() != 0L) {
        setBeaconAmount(other.getBeaconAmount());
      }
      if (other.getWitnessAmount() != 0L) {
        setWitnessAmount(other.getWitnessAmount());
      }
      if (other.getDcTransferAmount() != 0L) {
        setDcTransferAmount(other.getDcTransferAmount());
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
              hotspotKey_ = input.readBytes();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 16: {
              beaconAmount_ = input.readUInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              witnessAmount_ = input.readUInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              dcTransferAmount_ = input.readUInt64();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
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

    private com.google.protobuf.ByteString hotspotKey_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * &#47; Public key of the hotspot
     * </pre>
     *
     * <code>bytes hotspot_key = 1;</code>
     * @return The hotspotKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getHotspotKey() {
      return hotspotKey_;
    }
    /**
     * <pre>
     * &#47; Public key of the hotspot
     * </pre>
     *
     * <code>bytes hotspot_key = 1;</code>
     * @param value The hotspotKey to set.
     * @return This builder for chaining.
     */
    public Builder setHotspotKey(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      hotspotKey_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * &#47; Public key of the hotspot
     * </pre>
     *
     * <code>bytes hotspot_key = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearHotspotKey() {
      bitField0_ = (bitField0_ & ~0x00000001);
      hotspotKey_ = getDefaultInstance().getHotspotKey();
      onChanged();
      return this;
    }

    private long beaconAmount_ ;
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for beaconing
     * </pre>
     *
     * <code>uint64 beacon_amount = 2;</code>
     * @return The beaconAmount.
     */
    @java.lang.Override
    public long getBeaconAmount() {
      return beaconAmount_;
    }
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for beaconing
     * </pre>
     *
     * <code>uint64 beacon_amount = 2;</code>
     * @param value The beaconAmount to set.
     * @return This builder for chaining.
     */
    public Builder setBeaconAmount(long value) {

      beaconAmount_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for beaconing
     * </pre>
     *
     * <code>uint64 beacon_amount = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearBeaconAmount() {
      bitField0_ = (bitField0_ & ~0x00000002);
      beaconAmount_ = 0L;
      onChanged();
      return this;
    }

    private long witnessAmount_ ;
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for witnessing
     * </pre>
     *
     * <code>uint64 witness_amount = 3;</code>
     * @return The witnessAmount.
     */
    @java.lang.Override
    public long getWitnessAmount() {
      return witnessAmount_;
    }
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for witnessing
     * </pre>
     *
     * <code>uint64 witness_amount = 3;</code>
     * @param value The witnessAmount to set.
     * @return This builder for chaining.
     */
    public Builder setWitnessAmount(long value) {

      witnessAmount_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for witnessing
     * </pre>
     *
     * <code>uint64 witness_amount = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearWitnessAmount() {
      bitField0_ = (bitField0_ & ~0x00000004);
      witnessAmount_ = 0L;
      onChanged();
      return this;
    }

    private long dcTransferAmount_ ;
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for data transfer
     * </pre>
     *
     * <code>uint64 dc_transfer_amount = 4;</code>
     * @return The dcTransferAmount.
     */
    @java.lang.Override
    public long getDcTransferAmount() {
      return dcTransferAmount_;
    }
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for data transfer
     * </pre>
     *
     * <code>uint64 dc_transfer_amount = 4;</code>
     * @param value The dcTransferAmount to set.
     * @return This builder for chaining.
     */
    public Builder setDcTransferAmount(long value) {

      dcTransferAmount_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * &#47; Amount in iot bones credited to the hotspot for data transfer
     * </pre>
     *
     * <code>uint64 dc_transfer_amount = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearDcTransferAmount() {
      bitField0_ = (bitField0_ & ~0x00000008);
      dcTransferAmount_ = 0L;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helium.poc_lora.gateway_reward)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_lora.gateway_reward)
  private static final com.helium.grpc.gateway_reward DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.gateway_reward();
  }

  public static com.helium.grpc.gateway_reward getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<gateway_reward>
      PARSER = new com.google.protobuf.AbstractParser<gateway_reward>() {
    @java.lang.Override
    public gateway_reward parsePartialFrom(
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

  public static com.google.protobuf.Parser<gateway_reward> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<gateway_reward> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.gateway_reward getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

