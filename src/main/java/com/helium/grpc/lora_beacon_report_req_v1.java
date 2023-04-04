// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

/**
 * <pre>
 * beacon report as submitted by gateway to ingestor
 * </pre>
 *
 * Protobuf type {@code helium.poc_lora.lora_beacon_report_req_v1}
 */
public final class lora_beacon_report_req_v1 extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:helium.poc_lora.lora_beacon_report_req_v1)
    lora_beacon_report_req_v1OrBuilder {
private static final long serialVersionUID = 0L;
  // Use lora_beacon_report_req_v1.newBuilder() to construct.
  private lora_beacon_report_req_v1(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private lora_beacon_report_req_v1() {
    pubKey_ = com.google.protobuf.ByteString.EMPTY;
    localEntropy_ = com.google.protobuf.ByteString.EMPTY;
    remoteEntropy_ = com.google.protobuf.ByteString.EMPTY;
    data_ = com.google.protobuf.ByteString.EMPTY;
    datarate_ = 0;
    signature_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new lora_beacon_report_req_v1();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_beacon_report_req_v1_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_beacon_report_req_v1_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.lora_beacon_report_req_v1.class, com.helium.grpc.lora_beacon_report_req_v1.Builder.class);
  }

  public static final int PUB_KEY_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString pubKey_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes pub_key = 2;</code>
   * @return The pubKey.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getPubKey() {
    return pubKey_;
  }

  public static final int LOCAL_ENTROPY_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString localEntropy_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes local_entropy = 3;</code>
   * @return The localEntropy.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getLocalEntropy() {
    return localEntropy_;
  }

  public static final int REMOTE_ENTROPY_FIELD_NUMBER = 4;
  private com.google.protobuf.ByteString remoteEntropy_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes remote_entropy = 4;</code>
   * @return The remoteEntropy.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getRemoteEntropy() {
    return remoteEntropy_;
  }

  public static final int DATA_FIELD_NUMBER = 5;
  private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes data = 5;</code>
   * @return The data.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getData() {
    return data_;
  }

  public static final int FREQUENCY_FIELD_NUMBER = 6;
  private long frequency_ = 0L;
  /**
   * <pre>
   * Frequency in Hz
   * </pre>
   *
   * <code>uint64 frequency = 6;</code>
   * @return The frequency.
   */
  @java.lang.Override
  public long getFrequency() {
    return frequency_;
  }

  public static final int CHANNEL_FIELD_NUMBER = 7;
  private int channel_ = 0;
  /**
   * <code>int32 channel = 7;</code>
   * @return The channel.
   */
  @java.lang.Override
  public int getChannel() {
    return channel_;
  }

  public static final int DATARATE_FIELD_NUMBER = 8;
  private int datarate_ = 0;
  /**
   * <code>.helium.data_rate datarate = 8;</code>
   * @return The enum numeric value on the wire for datarate.
   */
  @java.lang.Override public int getDatarateValue() {
    return datarate_;
  }
  /**
   * <code>.helium.data_rate datarate = 8;</code>
   * @return The datarate.
   */
  @java.lang.Override public com.helium.grpc.data_rate getDatarate() {
    com.helium.grpc.data_rate result = com.helium.grpc.data_rate.forNumber(datarate_);
    return result == null ? com.helium.grpc.data_rate.UNRECOGNIZED : result;
  }

  public static final int TX_POWER_FIELD_NUMBER = 9;
  private int txPower_ = 0;
  /**
   * <pre>
   * The Conducted transmit power in ddbm. This is _not_ adjusted with the
   * asserted gain of the gateway
   * </pre>
   *
   * <code>int32 tx_power = 9;</code>
   * @return The txPower.
   */
  @java.lang.Override
  public int getTxPower() {
    return txPower_;
  }

  public static final int TIMESTAMP_FIELD_NUMBER = 10;
  private long timestamp_ = 0L;
  /**
   * <pre>
   * Timestamp of beacon transmit in nanos since unix epoch
   * </pre>
   *
   * <code>uint64 timestamp = 10;</code>
   * @return The timestamp.
   */
  @java.lang.Override
  public long getTimestamp() {
    return timestamp_;
  }

  public static final int SIGNATURE_FIELD_NUMBER = 11;
  private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes signature = 11;</code>
   * @return The signature.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getSignature() {
    return signature_;
  }

  public static final int TMST_FIELD_NUMBER = 12;
  private int tmst_ = 0;
  /**
   * <code>uint32 tmst = 12;</code>
   * @return The tmst.
   */
  @java.lang.Override
  public int getTmst() {
    return tmst_;
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
    if (!pubKey_.isEmpty()) {
      output.writeBytes(2, pubKey_);
    }
    if (!localEntropy_.isEmpty()) {
      output.writeBytes(3, localEntropy_);
    }
    if (!remoteEntropy_.isEmpty()) {
      output.writeBytes(4, remoteEntropy_);
    }
    if (!data_.isEmpty()) {
      output.writeBytes(5, data_);
    }
    if (frequency_ != 0L) {
      output.writeUInt64(6, frequency_);
    }
    if (channel_ != 0) {
      output.writeInt32(7, channel_);
    }
    if (datarate_ != com.helium.grpc.data_rate.SF12BW125.getNumber()) {
      output.writeEnum(8, datarate_);
    }
    if (txPower_ != 0) {
      output.writeInt32(9, txPower_);
    }
    if (timestamp_ != 0L) {
      output.writeUInt64(10, timestamp_);
    }
    if (!signature_.isEmpty()) {
      output.writeBytes(11, signature_);
    }
    if (tmst_ != 0) {
      output.writeUInt32(12, tmst_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!pubKey_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, pubKey_);
    }
    if (!localEntropy_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, localEntropy_);
    }
    if (!remoteEntropy_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(4, remoteEntropy_);
    }
    if (!data_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(5, data_);
    }
    if (frequency_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(6, frequency_);
    }
    if (channel_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, channel_);
    }
    if (datarate_ != com.helium.grpc.data_rate.SF12BW125.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(8, datarate_);
    }
    if (txPower_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, txPower_);
    }
    if (timestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(10, timestamp_);
    }
    if (!signature_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(11, signature_);
    }
    if (tmst_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(12, tmst_);
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
    if (!(obj instanceof com.helium.grpc.lora_beacon_report_req_v1)) {
      return super.equals(obj);
    }
    com.helium.grpc.lora_beacon_report_req_v1 other = (com.helium.grpc.lora_beacon_report_req_v1) obj;

    if (!getPubKey()
        .equals(other.getPubKey())) return false;
    if (!getLocalEntropy()
        .equals(other.getLocalEntropy())) return false;
    if (!getRemoteEntropy()
        .equals(other.getRemoteEntropy())) return false;
    if (!getData()
        .equals(other.getData())) return false;
    if (getFrequency()
        != other.getFrequency()) return false;
    if (getChannel()
        != other.getChannel()) return false;
    if (datarate_ != other.datarate_) return false;
    if (getTxPower()
        != other.getTxPower()) return false;
    if (getTimestamp()
        != other.getTimestamp()) return false;
    if (!getSignature()
        .equals(other.getSignature())) return false;
    if (getTmst()
        != other.getTmst()) return false;
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
    hash = (37 * hash) + PUB_KEY_FIELD_NUMBER;
    hash = (53 * hash) + getPubKey().hashCode();
    hash = (37 * hash) + LOCAL_ENTROPY_FIELD_NUMBER;
    hash = (53 * hash) + getLocalEntropy().hashCode();
    hash = (37 * hash) + REMOTE_ENTROPY_FIELD_NUMBER;
    hash = (53 * hash) + getRemoteEntropy().hashCode();
    hash = (37 * hash) + DATA_FIELD_NUMBER;
    hash = (53 * hash) + getData().hashCode();
    hash = (37 * hash) + FREQUENCY_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFrequency());
    hash = (37 * hash) + CHANNEL_FIELD_NUMBER;
    hash = (53 * hash) + getChannel();
    hash = (37 * hash) + DATARATE_FIELD_NUMBER;
    hash = (53 * hash) + datarate_;
    hash = (37 * hash) + TX_POWER_FIELD_NUMBER;
    hash = (53 * hash) + getTxPower();
    hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimestamp());
    hash = (37 * hash) + SIGNATURE_FIELD_NUMBER;
    hash = (53 * hash) + getSignature().hashCode();
    hash = (37 * hash) + TMST_FIELD_NUMBER;
    hash = (53 * hash) + getTmst();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.lora_beacon_report_req_v1 parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.lora_beacon_report_req_v1 prototype) {
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
   * <pre>
   * beacon report as submitted by gateway to ingestor
   * </pre>
   *
   * Protobuf type {@code helium.poc_lora.lora_beacon_report_req_v1}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_lora.lora_beacon_report_req_v1)
      com.helium.grpc.lora_beacon_report_req_v1OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_beacon_report_req_v1_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_beacon_report_req_v1_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.lora_beacon_report_req_v1.class, com.helium.grpc.lora_beacon_report_req_v1.Builder.class);
    }

    // Construct using com.helium.grpc.lora_beacon_report_req_v1.newBuilder()
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
      pubKey_ = com.google.protobuf.ByteString.EMPTY;
      localEntropy_ = com.google.protobuf.ByteString.EMPTY;
      remoteEntropy_ = com.google.protobuf.ByteString.EMPTY;
      data_ = com.google.protobuf.ByteString.EMPTY;
      frequency_ = 0L;
      channel_ = 0;
      datarate_ = 0;
      txPower_ = 0;
      timestamp_ = 0L;
      signature_ = com.google.protobuf.ByteString.EMPTY;
      tmst_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_beacon_report_req_v1_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.lora_beacon_report_req_v1 getDefaultInstanceForType() {
      return com.helium.grpc.lora_beacon_report_req_v1.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.lora_beacon_report_req_v1 build() {
      com.helium.grpc.lora_beacon_report_req_v1 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.lora_beacon_report_req_v1 buildPartial() {
      com.helium.grpc.lora_beacon_report_req_v1 result = new com.helium.grpc.lora_beacon_report_req_v1(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.lora_beacon_report_req_v1 result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.pubKey_ = pubKey_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.localEntropy_ = localEntropy_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.remoteEntropy_ = remoteEntropy_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.data_ = data_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.frequency_ = frequency_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.channel_ = channel_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.datarate_ = datarate_;
      }
      if (((from_bitField0_ & 0x00000080) != 0)) {
        result.txPower_ = txPower_;
      }
      if (((from_bitField0_ & 0x00000100) != 0)) {
        result.timestamp_ = timestamp_;
      }
      if (((from_bitField0_ & 0x00000200) != 0)) {
        result.signature_ = signature_;
      }
      if (((from_bitField0_ & 0x00000400) != 0)) {
        result.tmst_ = tmst_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.lora_beacon_report_req_v1) {
        return mergeFrom((com.helium.grpc.lora_beacon_report_req_v1)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.lora_beacon_report_req_v1 other) {
      if (other == com.helium.grpc.lora_beacon_report_req_v1.getDefaultInstance()) return this;
      if (other.getPubKey() != com.google.protobuf.ByteString.EMPTY) {
        setPubKey(other.getPubKey());
      }
      if (other.getLocalEntropy() != com.google.protobuf.ByteString.EMPTY) {
        setLocalEntropy(other.getLocalEntropy());
      }
      if (other.getRemoteEntropy() != com.google.protobuf.ByteString.EMPTY) {
        setRemoteEntropy(other.getRemoteEntropy());
      }
      if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
        setData(other.getData());
      }
      if (other.getFrequency() != 0L) {
        setFrequency(other.getFrequency());
      }
      if (other.getChannel() != 0) {
        setChannel(other.getChannel());
      }
      if (other.datarate_ != 0) {
        setDatarateValue(other.getDatarateValue());
      }
      if (other.getTxPower() != 0) {
        setTxPower(other.getTxPower());
      }
      if (other.getTimestamp() != 0L) {
        setTimestamp(other.getTimestamp());
      }
      if (other.getSignature() != com.google.protobuf.ByteString.EMPTY) {
        setSignature(other.getSignature());
      }
      if (other.getTmst() != 0) {
        setTmst(other.getTmst());
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
            case 18: {
              pubKey_ = input.readBytes();
              bitField0_ |= 0x00000001;
              break;
            } // case 18
            case 26: {
              localEntropy_ = input.readBytes();
              bitField0_ |= 0x00000002;
              break;
            } // case 26
            case 34: {
              remoteEntropy_ = input.readBytes();
              bitField0_ |= 0x00000004;
              break;
            } // case 34
            case 42: {
              data_ = input.readBytes();
              bitField0_ |= 0x00000008;
              break;
            } // case 42
            case 48: {
              frequency_ = input.readUInt64();
              bitField0_ |= 0x00000010;
              break;
            } // case 48
            case 56: {
              channel_ = input.readInt32();
              bitField0_ |= 0x00000020;
              break;
            } // case 56
            case 64: {
              datarate_ = input.readEnum();
              bitField0_ |= 0x00000040;
              break;
            } // case 64
            case 72: {
              txPower_ = input.readInt32();
              bitField0_ |= 0x00000080;
              break;
            } // case 72
            case 80: {
              timestamp_ = input.readUInt64();
              bitField0_ |= 0x00000100;
              break;
            } // case 80
            case 90: {
              signature_ = input.readBytes();
              bitField0_ |= 0x00000200;
              break;
            } // case 90
            case 96: {
              tmst_ = input.readUInt32();
              bitField0_ |= 0x00000400;
              break;
            } // case 96
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

    private com.google.protobuf.ByteString pubKey_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes pub_key = 2;</code>
     * @return The pubKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPubKey() {
      return pubKey_;
    }
    /**
     * <code>bytes pub_key = 2;</code>
     * @param value The pubKey to set.
     * @return This builder for chaining.
     */
    public Builder setPubKey(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      pubKey_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>bytes pub_key = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPubKey() {
      bitField0_ = (bitField0_ & ~0x00000001);
      pubKey_ = getDefaultInstance().getPubKey();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString localEntropy_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes local_entropy = 3;</code>
     * @return The localEntropy.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getLocalEntropy() {
      return localEntropy_;
    }
    /**
     * <code>bytes local_entropy = 3;</code>
     * @param value The localEntropy to set.
     * @return This builder for chaining.
     */
    public Builder setLocalEntropy(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      localEntropy_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>bytes local_entropy = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearLocalEntropy() {
      bitField0_ = (bitField0_ & ~0x00000002);
      localEntropy_ = getDefaultInstance().getLocalEntropy();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString remoteEntropy_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes remote_entropy = 4;</code>
     * @return The remoteEntropy.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getRemoteEntropy() {
      return remoteEntropy_;
    }
    /**
     * <code>bytes remote_entropy = 4;</code>
     * @param value The remoteEntropy to set.
     * @return This builder for chaining.
     */
    public Builder setRemoteEntropy(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      remoteEntropy_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>bytes remote_entropy = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearRemoteEntropy() {
      bitField0_ = (bitField0_ & ~0x00000004);
      remoteEntropy_ = getDefaultInstance().getRemoteEntropy();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes data = 5;</code>
     * @return The data.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getData() {
      return data_;
    }
    /**
     * <code>bytes data = 5;</code>
     * @param value The data to set.
     * @return This builder for chaining.
     */
    public Builder setData(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      data_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>bytes data = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearData() {
      bitField0_ = (bitField0_ & ~0x00000008);
      data_ = getDefaultInstance().getData();
      onChanged();
      return this;
    }

    private long frequency_ ;
    /**
     * <pre>
     * Frequency in Hz
     * </pre>
     *
     * <code>uint64 frequency = 6;</code>
     * @return The frequency.
     */
    @java.lang.Override
    public long getFrequency() {
      return frequency_;
    }
    /**
     * <pre>
     * Frequency in Hz
     * </pre>
     *
     * <code>uint64 frequency = 6;</code>
     * @param value The frequency to set.
     * @return This builder for chaining.
     */
    public Builder setFrequency(long value) {

      frequency_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Frequency in Hz
     * </pre>
     *
     * <code>uint64 frequency = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearFrequency() {
      bitField0_ = (bitField0_ & ~0x00000010);
      frequency_ = 0L;
      onChanged();
      return this;
    }

    private int channel_ ;
    /**
     * <code>int32 channel = 7;</code>
     * @return The channel.
     */
    @java.lang.Override
    public int getChannel() {
      return channel_;
    }
    /**
     * <code>int32 channel = 7;</code>
     * @param value The channel to set.
     * @return This builder for chaining.
     */
    public Builder setChannel(int value) {

      channel_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <code>int32 channel = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearChannel() {
      bitField0_ = (bitField0_ & ~0x00000020);
      channel_ = 0;
      onChanged();
      return this;
    }

    private int datarate_ = 0;
    /**
     * <code>.helium.data_rate datarate = 8;</code>
     * @return The enum numeric value on the wire for datarate.
     */
    @java.lang.Override public int getDatarateValue() {
      return datarate_;
    }
    /**
     * <code>.helium.data_rate datarate = 8;</code>
     * @param value The enum numeric value on the wire for datarate to set.
     * @return This builder for chaining.
     */
    public Builder setDatarateValue(int value) {
      datarate_ = value;
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <code>.helium.data_rate datarate = 8;</code>
     * @return The datarate.
     */
    @java.lang.Override
    public com.helium.grpc.data_rate getDatarate() {
      com.helium.grpc.data_rate result = com.helium.grpc.data_rate.forNumber(datarate_);
      return result == null ? com.helium.grpc.data_rate.UNRECOGNIZED : result;
    }
    /**
     * <code>.helium.data_rate datarate = 8;</code>
     * @param value The datarate to set.
     * @return This builder for chaining.
     */
    public Builder setDatarate(com.helium.grpc.data_rate value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000040;
      datarate_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.helium.data_rate datarate = 8;</code>
     * @return This builder for chaining.
     */
    public Builder clearDatarate() {
      bitField0_ = (bitField0_ & ~0x00000040);
      datarate_ = 0;
      onChanged();
      return this;
    }

    private int txPower_ ;
    /**
     * <pre>
     * The Conducted transmit power in ddbm. This is _not_ adjusted with the
     * asserted gain of the gateway
     * </pre>
     *
     * <code>int32 tx_power = 9;</code>
     * @return The txPower.
     */
    @java.lang.Override
    public int getTxPower() {
      return txPower_;
    }
    /**
     * <pre>
     * The Conducted transmit power in ddbm. This is _not_ adjusted with the
     * asserted gain of the gateway
     * </pre>
     *
     * <code>int32 tx_power = 9;</code>
     * @param value The txPower to set.
     * @return This builder for chaining.
     */
    public Builder setTxPower(int value) {

      txPower_ = value;
      bitField0_ |= 0x00000080;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The Conducted transmit power in ddbm. This is _not_ adjusted with the
     * asserted gain of the gateway
     * </pre>
     *
     * <code>int32 tx_power = 9;</code>
     * @return This builder for chaining.
     */
    public Builder clearTxPower() {
      bitField0_ = (bitField0_ & ~0x00000080);
      txPower_ = 0;
      onChanged();
      return this;
    }

    private long timestamp_ ;
    /**
     * <pre>
     * Timestamp of beacon transmit in nanos since unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 10;</code>
     * @return The timestamp.
     */
    @java.lang.Override
    public long getTimestamp() {
      return timestamp_;
    }
    /**
     * <pre>
     * Timestamp of beacon transmit in nanos since unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 10;</code>
     * @param value The timestamp to set.
     * @return This builder for chaining.
     */
    public Builder setTimestamp(long value) {

      timestamp_ = value;
      bitField0_ |= 0x00000100;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Timestamp of beacon transmit in nanos since unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 10;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000100);
      timestamp_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes signature = 11;</code>
     * @return The signature.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSignature() {
      return signature_;
    }
    /**
     * <code>bytes signature = 11;</code>
     * @param value The signature to set.
     * @return This builder for chaining.
     */
    public Builder setSignature(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      signature_ = value;
      bitField0_ |= 0x00000200;
      onChanged();
      return this;
    }
    /**
     * <code>bytes signature = 11;</code>
     * @return This builder for chaining.
     */
    public Builder clearSignature() {
      bitField0_ = (bitField0_ & ~0x00000200);
      signature_ = getDefaultInstance().getSignature();
      onChanged();
      return this;
    }

    private int tmst_ ;
    /**
     * <code>uint32 tmst = 12;</code>
     * @return The tmst.
     */
    @java.lang.Override
    public int getTmst() {
      return tmst_;
    }
    /**
     * <code>uint32 tmst = 12;</code>
     * @param value The tmst to set.
     * @return This builder for chaining.
     */
    public Builder setTmst(int value) {

      tmst_ = value;
      bitField0_ |= 0x00000400;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 tmst = 12;</code>
     * @return This builder for chaining.
     */
    public Builder clearTmst() {
      bitField0_ = (bitField0_ & ~0x00000400);
      tmst_ = 0;
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


    // @@protoc_insertion_point(builder_scope:helium.poc_lora.lora_beacon_report_req_v1)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_lora.lora_beacon_report_req_v1)
  private static final com.helium.grpc.lora_beacon_report_req_v1 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.lora_beacon_report_req_v1();
  }

  public static com.helium.grpc.lora_beacon_report_req_v1 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<lora_beacon_report_req_v1>
      PARSER = new com.google.protobuf.AbstractParser<lora_beacon_report_req_v1>() {
    @java.lang.Override
    public lora_beacon_report_req_v1 parsePartialFrom(
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

  public static com.google.protobuf.Parser<lora_beacon_report_req_v1> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<lora_beacon_report_req_v1> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.lora_beacon_report_req_v1 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
