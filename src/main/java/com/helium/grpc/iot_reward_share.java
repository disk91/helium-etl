// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

/**
 * Protobuf type {@code helium.poc_lora.iot_reward_share}
 */
public final class iot_reward_share extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:helium.poc_lora.iot_reward_share)
    iot_reward_shareOrBuilder {
private static final long serialVersionUID = 0L;
  // Use iot_reward_share.newBuilder() to construct.
  private iot_reward_share(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private iot_reward_share() {
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new iot_reward_share();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return PocLoRa.internal_static_helium_poc_lora_iot_reward_share_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return PocLoRa.internal_static_helium_poc_lora_iot_reward_share_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            iot_reward_share.class, Builder.class);
  }

  private int rewardCase_ = 0;
  @SuppressWarnings("serial")
  private Object reward_;
  public enum RewardCase
      implements com.google.protobuf.Internal.EnumLite,
          InternalOneOfEnum {
    GATEWAY_REWARD(3),
    OPERATIONAL_REWARD(4),
    REWARD_NOT_SET(0);
    private final int value;
    private RewardCase(int value) {
      this.value = value;
    }
    /**
     * @param value The number of the enum to look for.
     * @return The enum associated with the given number.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @Deprecated
    public static RewardCase valueOf(int value) {
      return forNumber(value);
    }

    public static RewardCase forNumber(int value) {
      switch (value) {
        case 3: return GATEWAY_REWARD;
        case 4: return OPERATIONAL_REWARD;
        case 0: return REWARD_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public RewardCase
  getRewardCase() {
    return RewardCase.forNumber(
        rewardCase_);
  }

  public static final int START_PERIOD_FIELD_NUMBER = 1;
  private long startPeriod_ = 0L;
  /**
   * <pre>
   *&#47; Unix timestamp in seconds of the start of the reward period
   * </pre>
   *
   * <code>uint64 start_period = 1;</code>
   * @return The startPeriod.
   */
  @Override
  public long getStartPeriod() {
    return startPeriod_;
  }

  public static final int END_PERIOD_FIELD_NUMBER = 2;
  private long endPeriod_ = 0L;
  /**
   * <pre>
   *&#47; Unix timestamp in seconds of the end of the reward period
   * </pre>
   *
   * <code>uint64 end_period = 2;</code>
   * @return The endPeriod.
   */
  @Override
  public long getEndPeriod() {
    return endPeriod_;
  }

  public static final int GATEWAY_REWARD_FIELD_NUMBER = 3;
  /**
   * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
   * @return Whether the gatewayReward field is set.
   */
  @Override
  public boolean hasGatewayReward() {
    return rewardCase_ == 3;
  }
  /**
   * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
   * @return The gatewayReward.
   */
  @Override
  public gateway_reward getGatewayReward() {
    if (rewardCase_ == 3) {
       return (gateway_reward) reward_;
    }
    return gateway_reward.getDefaultInstance();
  }
  /**
   * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
   */
  @Override
  public gateway_rewardOrBuilder getGatewayRewardOrBuilder() {
    if (rewardCase_ == 3) {
       return (gateway_reward) reward_;
    }
    return gateway_reward.getDefaultInstance();
  }

  public static final int OPERATIONAL_REWARD_FIELD_NUMBER = 4;
  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   * @return Whether the operationalReward field is set.
   */
  @Override
  public boolean hasOperationalReward() {
    return rewardCase_ == 4;
  }
  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   * @return The operationalReward.
   */
  @Override
  public operational_reward getOperationalReward() {
    if (rewardCase_ == 4) {
       return (operational_reward) reward_;
    }
    return operational_reward.getDefaultInstance();
  }
  /**
   * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
   */
  @Override
  public operational_rewardOrBuilder getOperationalRewardOrBuilder() {
    if (rewardCase_ == 4) {
       return (operational_reward) reward_;
    }
    return operational_reward.getDefaultInstance();
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (startPeriod_ != 0L) {
      output.writeUInt64(1, startPeriod_);
    }
    if (endPeriod_ != 0L) {
      output.writeUInt64(2, endPeriod_);
    }
    if (rewardCase_ == 3) {
      output.writeMessage(3, (gateway_reward) reward_);
    }
    if (rewardCase_ == 4) {
      output.writeMessage(4, (operational_reward) reward_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (startPeriod_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, startPeriod_);
    }
    if (endPeriod_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, endPeriod_);
    }
    if (rewardCase_ == 3) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, (gateway_reward) reward_);
    }
    if (rewardCase_ == 4) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, (operational_reward) reward_);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof iot_reward_share)) {
      return super.equals(obj);
    }
    iot_reward_share other = (iot_reward_share) obj;

    if (getStartPeriod()
        != other.getStartPeriod()) return false;
    if (getEndPeriod()
        != other.getEndPeriod()) return false;
    if (!getRewardCase().equals(other.getRewardCase())) return false;
    switch (rewardCase_) {
      case 3:
        if (!getGatewayReward()
            .equals(other.getGatewayReward())) return false;
        break;
      case 4:
        if (!getOperationalReward()
            .equals(other.getOperationalReward())) return false;
        break;
      case 0:
      default:
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + START_PERIOD_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getStartPeriod());
    hash = (37 * hash) + END_PERIOD_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getEndPeriod());
    switch (rewardCase_) {
      case 3:
        hash = (37 * hash) + GATEWAY_REWARD_FIELD_NUMBER;
        hash = (53 * hash) + getGatewayReward().hashCode();
        break;
      case 4:
        hash = (37 * hash) + OPERATIONAL_REWARD_FIELD_NUMBER;
        hash = (53 * hash) + getOperationalReward().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static iot_reward_share parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static iot_reward_share parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static iot_reward_share parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static iot_reward_share parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static iot_reward_share parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static iot_reward_share parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static iot_reward_share parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static iot_reward_share parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static iot_reward_share parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static iot_reward_share parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static iot_reward_share parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static iot_reward_share parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(iot_reward_share prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code helium.poc_lora.iot_reward_share}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_lora.iot_reward_share)
      iot_reward_shareOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return PocLoRa.internal_static_helium_poc_lora_iot_reward_share_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PocLoRa.internal_static_helium_poc_lora_iot_reward_share_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              iot_reward_share.class, Builder.class);
    }

    // Construct using com.helium.grpc.iot_reward_share.newBuilder()
    private Builder() {

    }

    private Builder(
        BuilderParent parent) {
      super(parent);

    }
    @Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      startPeriod_ = 0L;
      endPeriod_ = 0L;
      if (gatewayRewardBuilder_ != null) {
        gatewayRewardBuilder_.clear();
      }
      if (operationalRewardBuilder_ != null) {
        operationalRewardBuilder_.clear();
      }
      rewardCase_ = 0;
      reward_ = null;
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return PocLoRa.internal_static_helium_poc_lora_iot_reward_share_descriptor;
    }

    @Override
    public iot_reward_share getDefaultInstanceForType() {
      return iot_reward_share.getDefaultInstance();
    }

    @Override
    public iot_reward_share build() {
      iot_reward_share result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public iot_reward_share buildPartial() {
      iot_reward_share result = new iot_reward_share(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      buildPartialOneofs(result);
      onBuilt();
      return result;
    }

    private void buildPartial0(iot_reward_share result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.startPeriod_ = startPeriod_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.endPeriod_ = endPeriod_;
      }
    }

    private void buildPartialOneofs(iot_reward_share result) {
      result.rewardCase_ = rewardCase_;
      result.reward_ = this.reward_;
      if (rewardCase_ == 3 &&
          gatewayRewardBuilder_ != null) {
        result.reward_ = gatewayRewardBuilder_.build();
      }
      if (rewardCase_ == 4 &&
          operationalRewardBuilder_ != null) {
        result.reward_ = operationalRewardBuilder_.build();
      }
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof iot_reward_share) {
        return mergeFrom((iot_reward_share)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(iot_reward_share other) {
      if (other == iot_reward_share.getDefaultInstance()) return this;
      if (other.getStartPeriod() != 0L) {
        setStartPeriod(other.getStartPeriod());
      }
      if (other.getEndPeriod() != 0L) {
        setEndPeriod(other.getEndPeriod());
      }
      switch (other.getRewardCase()) {
        case GATEWAY_REWARD: {
          mergeGatewayReward(other.getGatewayReward());
          break;
        }
        case OPERATIONAL_REWARD: {
          mergeOperationalReward(other.getOperationalReward());
          break;
        }
        case REWARD_NOT_SET: {
          break;
        }
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new NullPointerException();
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
              startPeriod_ = input.readUInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              endPeriod_ = input.readUInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 26: {
              input.readMessage(
                  getGatewayRewardFieldBuilder().getBuilder(),
                  extensionRegistry);
              rewardCase_ = 3;
              break;
            } // case 26
            case 34: {
              input.readMessage(
                  getOperationalRewardFieldBuilder().getBuilder(),
                  extensionRegistry);
              rewardCase_ = 4;
              break;
            } // case 34
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
    private int rewardCase_ = 0;
    private Object reward_;
    public RewardCase
        getRewardCase() {
      return RewardCase.forNumber(
          rewardCase_);
    }

    public Builder clearReward() {
      rewardCase_ = 0;
      reward_ = null;
      onChanged();
      return this;
    }

    private int bitField0_;

    private long startPeriod_ ;
    /**
     * <pre>
     *&#47; Unix timestamp in seconds of the start of the reward period
     * </pre>
     *
     * <code>uint64 start_period = 1;</code>
     * @return The startPeriod.
     */
    @Override
    public long getStartPeriod() {
      return startPeriod_;
    }
    /**
     * <pre>
     *&#47; Unix timestamp in seconds of the start of the reward period
     * </pre>
     *
     * <code>uint64 start_period = 1;</code>
     * @param value The startPeriod to set.
     * @return This builder for chaining.
     */
    public Builder setStartPeriod(long value) {

      startPeriod_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *&#47; Unix timestamp in seconds of the start of the reward period
     * </pre>
     *
     * <code>uint64 start_period = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearStartPeriod() {
      bitField0_ = (bitField0_ & ~0x00000001);
      startPeriod_ = 0L;
      onChanged();
      return this;
    }

    private long endPeriod_ ;
    /**
     * <pre>
     *&#47; Unix timestamp in seconds of the end of the reward period
     * </pre>
     *
     * <code>uint64 end_period = 2;</code>
     * @return The endPeriod.
     */
    @Override
    public long getEndPeriod() {
      return endPeriod_;
    }
    /**
     * <pre>
     *&#47; Unix timestamp in seconds of the end of the reward period
     * </pre>
     *
     * <code>uint64 end_period = 2;</code>
     * @param value The endPeriod to set.
     * @return This builder for chaining.
     */
    public Builder setEndPeriod(long value) {

      endPeriod_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     *&#47; Unix timestamp in seconds of the end of the reward period
     * </pre>
     *
     * <code>uint64 end_period = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearEndPeriod() {
      bitField0_ = (bitField0_ & ~0x00000002);
      endPeriod_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        gateway_reward, gateway_reward.Builder, gateway_rewardOrBuilder> gatewayRewardBuilder_;
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     * @return Whether the gatewayReward field is set.
     */
    @Override
    public boolean hasGatewayReward() {
      return rewardCase_ == 3;
    }
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     * @return The gatewayReward.
     */
    @Override
    public gateway_reward getGatewayReward() {
      if (gatewayRewardBuilder_ == null) {
        if (rewardCase_ == 3) {
          return (gateway_reward) reward_;
        }
        return gateway_reward.getDefaultInstance();
      } else {
        if (rewardCase_ == 3) {
          return gatewayRewardBuilder_.getMessage();
        }
        return gateway_reward.getDefaultInstance();
      }
    }
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     */
    public Builder setGatewayReward(gateway_reward value) {
      if (gatewayRewardBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        reward_ = value;
        onChanged();
      } else {
        gatewayRewardBuilder_.setMessage(value);
      }
      rewardCase_ = 3;
      return this;
    }
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     */
    public Builder setGatewayReward(
        gateway_reward.Builder builderForValue) {
      if (gatewayRewardBuilder_ == null) {
        reward_ = builderForValue.build();
        onChanged();
      } else {
        gatewayRewardBuilder_.setMessage(builderForValue.build());
      }
      rewardCase_ = 3;
      return this;
    }
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     */
    public Builder mergeGatewayReward(gateway_reward value) {
      if (gatewayRewardBuilder_ == null) {
        if (rewardCase_ == 3 &&
            reward_ != gateway_reward.getDefaultInstance()) {
          reward_ = gateway_reward.newBuilder((gateway_reward) reward_)
              .mergeFrom(value).buildPartial();
        } else {
          reward_ = value;
        }
        onChanged();
      } else {
        if (rewardCase_ == 3) {
          gatewayRewardBuilder_.mergeFrom(value);
        } else {
          gatewayRewardBuilder_.setMessage(value);
        }
      }
      rewardCase_ = 3;
      return this;
    }
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     */
    public Builder clearGatewayReward() {
      if (gatewayRewardBuilder_ == null) {
        if (rewardCase_ == 3) {
          rewardCase_ = 0;
          reward_ = null;
          onChanged();
        }
      } else {
        if (rewardCase_ == 3) {
          rewardCase_ = 0;
          reward_ = null;
        }
        gatewayRewardBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     */
    public gateway_reward.Builder getGatewayRewardBuilder() {
      return getGatewayRewardFieldBuilder().getBuilder();
    }
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     */
    @Override
    public gateway_rewardOrBuilder getGatewayRewardOrBuilder() {
      if ((rewardCase_ == 3) && (gatewayRewardBuilder_ != null)) {
        return gatewayRewardBuilder_.getMessageOrBuilder();
      } else {
        if (rewardCase_ == 3) {
          return (gateway_reward) reward_;
        }
        return gateway_reward.getDefaultInstance();
      }
    }
    /**
     * <code>.helium.poc_lora.gateway_reward gateway_reward = 3;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        gateway_reward, gateway_reward.Builder, gateway_rewardOrBuilder>
        getGatewayRewardFieldBuilder() {
      if (gatewayRewardBuilder_ == null) {
        if (!(rewardCase_ == 3)) {
          reward_ = gateway_reward.getDefaultInstance();
        }
        gatewayRewardBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            gateway_reward, gateway_reward.Builder, gateway_rewardOrBuilder>(
                (gateway_reward) reward_,
                getParentForChildren(),
                isClean());
        reward_ = null;
      }
      rewardCase_ = 3;
      onChanged();
      return gatewayRewardBuilder_;
    }

    private com.google.protobuf.SingleFieldBuilderV3<
        operational_reward, operational_reward.Builder, operational_rewardOrBuilder> operationalRewardBuilder_;
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     * @return Whether the operationalReward field is set.
     */
    @Override
    public boolean hasOperationalReward() {
      return rewardCase_ == 4;
    }
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     * @return The operationalReward.
     */
    @Override
    public operational_reward getOperationalReward() {
      if (operationalRewardBuilder_ == null) {
        if (rewardCase_ == 4) {
          return (operational_reward) reward_;
        }
        return operational_reward.getDefaultInstance();
      } else {
        if (rewardCase_ == 4) {
          return operationalRewardBuilder_.getMessage();
        }
        return operational_reward.getDefaultInstance();
      }
    }
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     */
    public Builder setOperationalReward(operational_reward value) {
      if (operationalRewardBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        reward_ = value;
        onChanged();
      } else {
        operationalRewardBuilder_.setMessage(value);
      }
      rewardCase_ = 4;
      return this;
    }
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     */
    public Builder setOperationalReward(
        operational_reward.Builder builderForValue) {
      if (operationalRewardBuilder_ == null) {
        reward_ = builderForValue.build();
        onChanged();
      } else {
        operationalRewardBuilder_.setMessage(builderForValue.build());
      }
      rewardCase_ = 4;
      return this;
    }
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     */
    public Builder mergeOperationalReward(operational_reward value) {
      if (operationalRewardBuilder_ == null) {
        if (rewardCase_ == 4 &&
            reward_ != operational_reward.getDefaultInstance()) {
          reward_ = operational_reward.newBuilder((operational_reward) reward_)
              .mergeFrom(value).buildPartial();
        } else {
          reward_ = value;
        }
        onChanged();
      } else {
        if (rewardCase_ == 4) {
          operationalRewardBuilder_.mergeFrom(value);
        } else {
          operationalRewardBuilder_.setMessage(value);
        }
      }
      rewardCase_ = 4;
      return this;
    }
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     */
    public Builder clearOperationalReward() {
      if (operationalRewardBuilder_ == null) {
        if (rewardCase_ == 4) {
          rewardCase_ = 0;
          reward_ = null;
          onChanged();
        }
      } else {
        if (rewardCase_ == 4) {
          rewardCase_ = 0;
          reward_ = null;
        }
        operationalRewardBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     */
    public operational_reward.Builder getOperationalRewardBuilder() {
      return getOperationalRewardFieldBuilder().getBuilder();
    }
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     */
    @Override
    public operational_rewardOrBuilder getOperationalRewardOrBuilder() {
      if ((rewardCase_ == 4) && (operationalRewardBuilder_ != null)) {
        return operationalRewardBuilder_.getMessageOrBuilder();
      } else {
        if (rewardCase_ == 4) {
          return (operational_reward) reward_;
        }
        return operational_reward.getDefaultInstance();
      }
    }
    /**
     * <code>.helium.poc_lora.operational_reward operational_reward = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        operational_reward, operational_reward.Builder, operational_rewardOrBuilder>
        getOperationalRewardFieldBuilder() {
      if (operationalRewardBuilder_ == null) {
        if (!(rewardCase_ == 4)) {
          reward_ = operational_reward.getDefaultInstance();
        }
        operationalRewardBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            operational_reward, operational_reward.Builder, operational_rewardOrBuilder>(
                (operational_reward) reward_,
                getParentForChildren(),
                isClean());
        reward_ = null;
      }
      rewardCase_ = 4;
      onChanged();
      return operationalRewardBuilder_;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:helium.poc_lora.iot_reward_share)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_lora.iot_reward_share)
  private static final iot_reward_share DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new iot_reward_share();
  }

  public static iot_reward_share getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<iot_reward_share>
      PARSER = new com.google.protobuf.AbstractParser<iot_reward_share>() {
    @Override
    public iot_reward_share parsePartialFrom(
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

  public static com.google.protobuf.Parser<iot_reward_share> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<iot_reward_share> getParserForType() {
    return PARSER;
  }

  @Override
  public iot_reward_share getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

