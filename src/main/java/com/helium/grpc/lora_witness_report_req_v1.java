// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

/**
 * <pre>
 * witness report as submitted by gateway to ingestor
 * </pre>
 *
 * Protobuf type {@code helium.poc_lora.lora_witness_report_req_v1}
 */
public final class lora_witness_report_req_v1 extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:helium.poc_lora.lora_witness_report_req_v1)
    lora_witness_report_req_v1OrBuilder {
private static final long serialVersionUID = 0L;
  // Use lora_witness_report_req_v1.newBuilder() to construct.
  private lora_witness_report_req_v1(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private lora_witness_report_req_v1() {
    pubKey_ = com.google.protobuf.ByteString.EMPTY;
    data_ = com.google.protobuf.ByteString.EMPTY;
    datarate_ = 0;
    signature_ = com.google.protobuf.ByteString.EMPTY;
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new lora_witness_report_req_v1();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return PocLoRa.internal_static_helium_poc_lora_lora_witness_report_req_v1_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return PocLoRa.internal_static_helium_poc_lora_lora_witness_report_req_v1_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            lora_witness_report_req_v1.class, Builder.class);
  }

  public static final int PUB_KEY_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString pubKey_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes pub_key = 2;</code>
   * @return The pubKey.
   */
  @Override
  public com.google.protobuf.ByteString getPubKey() {
    return pubKey_;
  }

  public static final int DATA_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes data = 3;</code>
   * @return The data.
   */
  @Override
  public com.google.protobuf.ByteString getData() {
    return data_;
  }

  public static final int TIMESTAMP_FIELD_NUMBER = 4;
  private long timestamp_ = 0L;
  /**
   * <pre>
   * Timestamp of witness received in nanos since unix epoch
   * </pre>
   *
   * <code>uint64 timestamp = 4;</code>
   * @return The timestamp.
   */
  @Override
  public long getTimestamp() {
    return timestamp_;
  }

  public static final int TMST_FIELD_NUMBER = 5;
  private int tmst_ = 0;
  /**
   * <code>uint32 tmst = 5;</code>
   * @return The tmst.
   */
  @Override
  public int getTmst() {
    return tmst_;
  }

  public static final int SIGNAL_FIELD_NUMBER = 6;
  private int signal_ = 0;
  /**
   * <pre>
   * Signal in ddbm
   * </pre>
   *
   * <code>sint32 signal = 6;</code>
   * @return The signal.
   */
  @Override
  public int getSignal() {
    return signal_;
  }

  public static final int SNR_FIELD_NUMBER = 7;
  private int snr_ = 0;
  /**
   * <code>int32 snr = 7;</code>
   * @return The snr.
   */
  @Override
  public int getSnr() {
    return snr_;
  }

  public static final int FREQUENCY_FIELD_NUMBER = 8;
  private long frequency_ = 0L;
  /**
   * <pre>
   * Frequency in Hz
   * </pre>
   *
   * <code>uint64 frequency = 8;</code>
   * @return The frequency.
   */
  @Override
  public long getFrequency() {
    return frequency_;
  }

  public static final int DATARATE_FIELD_NUMBER = 10;
  private int datarate_ = 0;
  /**
   * <code>.helium.data_rate datarate = 10;</code>
   * @return The enum numeric value on the wire for datarate.
   */
  @Override public int getDatarateValue() {
    return datarate_;
  }
  /**
   * <code>.helium.data_rate datarate = 10;</code>
   * @return The datarate.
   */
  @Override public data_rate getDatarate() {
    data_rate result = data_rate.forNumber(datarate_);
    return result == null ? data_rate.UNRECOGNIZED : result;
  }

  public static final int SIGNATURE_FIELD_NUMBER = 11;
  private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes signature = 11;</code>
   * @return The signature.
   */
  @Override
  public com.google.protobuf.ByteString getSignature() {
    return signature_;
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
    if (!pubKey_.isEmpty()) {
      output.writeBytes(2, pubKey_);
    }
    if (!data_.isEmpty()) {
      output.writeBytes(3, data_);
    }
    if (timestamp_ != 0L) {
      output.writeUInt64(4, timestamp_);
    }
    if (tmst_ != 0) {
      output.writeUInt32(5, tmst_);
    }
    if (signal_ != 0) {
      output.writeSInt32(6, signal_);
    }
    if (snr_ != 0) {
      output.writeInt32(7, snr_);
    }
    if (frequency_ != 0L) {
      output.writeUInt64(8, frequency_);
    }
    if (datarate_ != data_rate.SF12BW125.getNumber()) {
      output.writeEnum(10, datarate_);
    }
    if (!signature_.isEmpty()) {
      output.writeBytes(11, signature_);
    }
    getUnknownFields().writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!pubKey_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, pubKey_);
    }
    if (!data_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, data_);
    }
    if (timestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, timestamp_);
    }
    if (tmst_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(5, tmst_);
    }
    if (signal_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeSInt32Size(6, signal_);
    }
    if (snr_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, snr_);
    }
    if (frequency_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(8, frequency_);
    }
    if (datarate_ != data_rate.SF12BW125.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(10, datarate_);
    }
    if (!signature_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(11, signature_);
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
    if (!(obj instanceof lora_witness_report_req_v1)) {
      return super.equals(obj);
    }
    lora_witness_report_req_v1 other = (lora_witness_report_req_v1) obj;

    if (!getPubKey()
        .equals(other.getPubKey())) return false;
    if (!getData()
        .equals(other.getData())) return false;
    if (getTimestamp()
        != other.getTimestamp()) return false;
    if (getTmst()
        != other.getTmst()) return false;
    if (getSignal()
        != other.getSignal()) return false;
    if (getSnr()
        != other.getSnr()) return false;
    if (getFrequency()
        != other.getFrequency()) return false;
    if (datarate_ != other.datarate_) return false;
    if (!getSignature()
        .equals(other.getSignature())) return false;
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
    hash = (37 * hash) + PUB_KEY_FIELD_NUMBER;
    hash = (53 * hash) + getPubKey().hashCode();
    hash = (37 * hash) + DATA_FIELD_NUMBER;
    hash = (53 * hash) + getData().hashCode();
    hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimestamp());
    hash = (37 * hash) + TMST_FIELD_NUMBER;
    hash = (53 * hash) + getTmst();
    hash = (37 * hash) + SIGNAL_FIELD_NUMBER;
    hash = (53 * hash) + getSignal();
    hash = (37 * hash) + SNR_FIELD_NUMBER;
    hash = (53 * hash) + getSnr();
    hash = (37 * hash) + FREQUENCY_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFrequency());
    hash = (37 * hash) + DATARATE_FIELD_NUMBER;
    hash = (53 * hash) + datarate_;
    hash = (37 * hash) + SIGNATURE_FIELD_NUMBER;
    hash = (53 * hash) + getSignature().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static lora_witness_report_req_v1 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lora_witness_report_req_v1 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lora_witness_report_req_v1 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lora_witness_report_req_v1 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lora_witness_report_req_v1 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static lora_witness_report_req_v1 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static lora_witness_report_req_v1 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static lora_witness_report_req_v1 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static lora_witness_report_req_v1 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static lora_witness_report_req_v1 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static lora_witness_report_req_v1 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static lora_witness_report_req_v1 parseFrom(
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
  public static Builder newBuilder(lora_witness_report_req_v1 prototype) {
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
   * <pre>
   * witness report as submitted by gateway to ingestor
   * </pre>
   *
   * Protobuf type {@code helium.poc_lora.lora_witness_report_req_v1}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_lora.lora_witness_report_req_v1)
      lora_witness_report_req_v1OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return PocLoRa.internal_static_helium_poc_lora_lora_witness_report_req_v1_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return PocLoRa.internal_static_helium_poc_lora_lora_witness_report_req_v1_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              lora_witness_report_req_v1.class, Builder.class);
    }

    // Construct using com.helium.grpc.lora_witness_report_req_v1.newBuilder()
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
      pubKey_ = com.google.protobuf.ByteString.EMPTY;
      data_ = com.google.protobuf.ByteString.EMPTY;
      timestamp_ = 0L;
      tmst_ = 0;
      signal_ = 0;
      snr_ = 0;
      frequency_ = 0L;
      datarate_ = 0;
      signature_ = com.google.protobuf.ByteString.EMPTY;
      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return PocLoRa.internal_static_helium_poc_lora_lora_witness_report_req_v1_descriptor;
    }

    @Override
    public lora_witness_report_req_v1 getDefaultInstanceForType() {
      return lora_witness_report_req_v1.getDefaultInstance();
    }

    @Override
    public lora_witness_report_req_v1 build() {
      lora_witness_report_req_v1 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public lora_witness_report_req_v1 buildPartial() {
      lora_witness_report_req_v1 result = new lora_witness_report_req_v1(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(lora_witness_report_req_v1 result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.pubKey_ = pubKey_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.data_ = data_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.timestamp_ = timestamp_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.tmst_ = tmst_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.signal_ = signal_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.snr_ = snr_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.frequency_ = frequency_;
      }
      if (((from_bitField0_ & 0x00000080) != 0)) {
        result.datarate_ = datarate_;
      }
      if (((from_bitField0_ & 0x00000100) != 0)) {
        result.signature_ = signature_;
      }
    }

    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof lora_witness_report_req_v1) {
        return mergeFrom((lora_witness_report_req_v1)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(lora_witness_report_req_v1 other) {
      if (other == lora_witness_report_req_v1.getDefaultInstance()) return this;
      if (other.getPubKey() != com.google.protobuf.ByteString.EMPTY) {
        setPubKey(other.getPubKey());
      }
      if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
        setData(other.getData());
      }
      if (other.getTimestamp() != 0L) {
        setTimestamp(other.getTimestamp());
      }
      if (other.getTmst() != 0) {
        setTmst(other.getTmst());
      }
      if (other.getSignal() != 0) {
        setSignal(other.getSignal());
      }
      if (other.getSnr() != 0) {
        setSnr(other.getSnr());
      }
      if (other.getFrequency() != 0L) {
        setFrequency(other.getFrequency());
      }
      if (other.datarate_ != 0) {
        setDatarateValue(other.getDatarateValue());
      }
      if (other.getSignature() != com.google.protobuf.ByteString.EMPTY) {
        setSignature(other.getSignature());
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
            case 18: {
              pubKey_ = input.readBytes();
              bitField0_ |= 0x00000001;
              break;
            } // case 18
            case 26: {
              data_ = input.readBytes();
              bitField0_ |= 0x00000002;
              break;
            } // case 26
            case 32: {
              timestamp_ = input.readUInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 32
            case 40: {
              tmst_ = input.readUInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 40
            case 48: {
              signal_ = input.readSInt32();
              bitField0_ |= 0x00000010;
              break;
            } // case 48
            case 56: {
              snr_ = input.readInt32();
              bitField0_ |= 0x00000020;
              break;
            } // case 56
            case 64: {
              frequency_ = input.readUInt64();
              bitField0_ |= 0x00000040;
              break;
            } // case 64
            case 80: {
              datarate_ = input.readEnum();
              bitField0_ |= 0x00000080;
              break;
            } // case 80
            case 90: {
              signature_ = input.readBytes();
              bitField0_ |= 0x00000100;
              break;
            } // case 90
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
    @Override
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

    private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes data = 3;</code>
     * @return The data.
     */
    @Override
    public com.google.protobuf.ByteString getData() {
      return data_;
    }
    /**
     * <code>bytes data = 3;</code>
     * @param value The data to set.
     * @return This builder for chaining.
     */
    public Builder setData(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      data_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>bytes data = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearData() {
      bitField0_ = (bitField0_ & ~0x00000002);
      data_ = getDefaultInstance().getData();
      onChanged();
      return this;
    }

    private long timestamp_ ;
    /**
     * <pre>
     * Timestamp of witness received in nanos since unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 4;</code>
     * @return The timestamp.
     */
    @Override
    public long getTimestamp() {
      return timestamp_;
    }
    /**
     * <pre>
     * Timestamp of witness received in nanos since unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 4;</code>
     * @param value The timestamp to set.
     * @return This builder for chaining.
     */
    public Builder setTimestamp(long value) {

      timestamp_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Timestamp of witness received in nanos since unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000004);
      timestamp_ = 0L;
      onChanged();
      return this;
    }

    private int tmst_ ;
    /**
     * <code>uint32 tmst = 5;</code>
     * @return The tmst.
     */
    @Override
    public int getTmst() {
      return tmst_;
    }
    /**
     * <code>uint32 tmst = 5;</code>
     * @param value The tmst to set.
     * @return This builder for chaining.
     */
    public Builder setTmst(int value) {

      tmst_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 tmst = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearTmst() {
      bitField0_ = (bitField0_ & ~0x00000008);
      tmst_ = 0;
      onChanged();
      return this;
    }

    private int signal_ ;
    /**
     * <pre>
     * Signal in ddbm
     * </pre>
     *
     * <code>sint32 signal = 6;</code>
     * @return The signal.
     */
    @Override
    public int getSignal() {
      return signal_;
    }
    /**
     * <pre>
     * Signal in ddbm
     * </pre>
     *
     * <code>sint32 signal = 6;</code>
     * @param value The signal to set.
     * @return This builder for chaining.
     */
    public Builder setSignal(int value) {

      signal_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Signal in ddbm
     * </pre>
     *
     * <code>sint32 signal = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearSignal() {
      bitField0_ = (bitField0_ & ~0x00000010);
      signal_ = 0;
      onChanged();
      return this;
    }

    private int snr_ ;
    /**
     * <code>int32 snr = 7;</code>
     * @return The snr.
     */
    @Override
    public int getSnr() {
      return snr_;
    }
    /**
     * <code>int32 snr = 7;</code>
     * @param value The snr to set.
     * @return This builder for chaining.
     */
    public Builder setSnr(int value) {

      snr_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <code>int32 snr = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearSnr() {
      bitField0_ = (bitField0_ & ~0x00000020);
      snr_ = 0;
      onChanged();
      return this;
    }

    private long frequency_ ;
    /**
     * <pre>
     * Frequency in Hz
     * </pre>
     *
     * <code>uint64 frequency = 8;</code>
     * @return The frequency.
     */
    @Override
    public long getFrequency() {
      return frequency_;
    }
    /**
     * <pre>
     * Frequency in Hz
     * </pre>
     *
     * <code>uint64 frequency = 8;</code>
     * @param value The frequency to set.
     * @return This builder for chaining.
     */
    public Builder setFrequency(long value) {

      frequency_ = value;
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Frequency in Hz
     * </pre>
     *
     * <code>uint64 frequency = 8;</code>
     * @return This builder for chaining.
     */
    public Builder clearFrequency() {
      bitField0_ = (bitField0_ & ~0x00000040);
      frequency_ = 0L;
      onChanged();
      return this;
    }

    private int datarate_ = 0;
    /**
     * <code>.helium.data_rate datarate = 10;</code>
     * @return The enum numeric value on the wire for datarate.
     */
    @Override public int getDatarateValue() {
      return datarate_;
    }
    /**
     * <code>.helium.data_rate datarate = 10;</code>
     * @param value The enum numeric value on the wire for datarate to set.
     * @return This builder for chaining.
     */
    public Builder setDatarateValue(int value) {
      datarate_ = value;
      bitField0_ |= 0x00000080;
      onChanged();
      return this;
    }
    /**
     * <code>.helium.data_rate datarate = 10;</code>
     * @return The datarate.
     */
    @Override
    public data_rate getDatarate() {
      data_rate result = data_rate.forNumber(datarate_);
      return result == null ? data_rate.UNRECOGNIZED : result;
    }
    /**
     * <code>.helium.data_rate datarate = 10;</code>
     * @param value The datarate to set.
     * @return This builder for chaining.
     */
    public Builder setDatarate(data_rate value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000080;
      datarate_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.helium.data_rate datarate = 10;</code>
     * @return This builder for chaining.
     */
    public Builder clearDatarate() {
      bitField0_ = (bitField0_ & ~0x00000080);
      datarate_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes signature = 11;</code>
     * @return The signature.
     */
    @Override
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
      bitField0_ |= 0x00000100;
      onChanged();
      return this;
    }
    /**
     * <code>bytes signature = 11;</code>
     * @return This builder for chaining.
     */
    public Builder clearSignature() {
      bitField0_ = (bitField0_ & ~0x00000100);
      signature_ = getDefaultInstance().getSignature();
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:helium.poc_lora.lora_witness_report_req_v1)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_lora.lora_witness_report_req_v1)
  private static final lora_witness_report_req_v1 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new lora_witness_report_req_v1();
  }

  public static lora_witness_report_req_v1 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<lora_witness_report_req_v1>
      PARSER = new com.google.protobuf.AbstractParser<lora_witness_report_req_v1>() {
    @Override
    public lora_witness_report_req_v1 parsePartialFrom(
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

  public static com.google.protobuf.Parser<lora_witness_report_req_v1> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<lora_witness_report_req_v1> getParserForType() {
    return PARSER;
  }

  @Override
  public lora_witness_report_req_v1 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

