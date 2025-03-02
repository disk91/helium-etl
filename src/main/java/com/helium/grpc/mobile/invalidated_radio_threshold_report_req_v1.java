// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf type {@code helium.poc_mobile.invalidated_radio_threshold_report_req_v1}
 */
public final class invalidated_radio_threshold_report_req_v1 extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:helium.poc_mobile.invalidated_radio_threshold_report_req_v1)
    invalidated_radio_threshold_report_req_v1OrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      invalidated_radio_threshold_report_req_v1.class.getName());
  }
  // Use invalidated_radio_threshold_report_req_v1.newBuilder() to construct.
  private invalidated_radio_threshold_report_req_v1(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private invalidated_radio_threshold_report_req_v1() {
    cbsdId_ = "";
    hotspotPubkey_ = com.google.protobuf.ByteString.EMPTY;
    reason_ = 0;
    carrierPubKey_ = com.google.protobuf.ByteString.EMPTY;
    signature_ = com.google.protobuf.ByteString.EMPTY;
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_invalidated_radio_threshold_report_req_v1_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_invalidated_radio_threshold_report_req_v1_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1.class, com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1.Builder.class);
  }

  public static final int CBSD_ID_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object cbsdId_ = "";
  /**
   * <pre>
   * the id of the radio which thresholds are invalidated
   * </pre>
   *
   * <code>string cbsd_id = 1;</code>
   * @return The cbsdId.
   */
  @java.lang.Override
  public java.lang.String getCbsdId() {
    java.lang.Object ref = cbsdId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      cbsdId_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * the id of the radio which thresholds are invalidated
   * </pre>
   *
   * <code>string cbsd_id = 1;</code>
   * @return The bytes for cbsdId.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCbsdIdBytes() {
    java.lang.Object ref = cbsdId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      cbsdId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int HOTSPOT_PUBKEY_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString hotspotPubkey_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <pre>
   * The onchain address of the gateway
   * </pre>
   *
   * <code>bytes hotspot_pubkey = 2;</code>
   * @return The hotspotPubkey.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getHotspotPubkey() {
    return hotspotPubkey_;
  }

  public static final int REASON_FIELD_NUMBER = 3;
  private int reason_ = 0;
  /**
   * <pre>
   * the reason the thresholds are invalidated
   * </pre>
   *
   * <code>.helium.poc_mobile.invalidated_threshold_reason reason = 3;</code>
   * @return The enum numeric value on the wire for reason.
   */
  @java.lang.Override public int getReasonValue() {
    return reason_;
  }
  /**
   * <pre>
   * the reason the thresholds are invalidated
   * </pre>
   *
   * <code>.helium.poc_mobile.invalidated_threshold_reason reason = 3;</code>
   * @return The reason.
   */
  @java.lang.Override public com.helium.grpc.mobile.invalidated_threshold_reason getReason() {
    com.helium.grpc.mobile.invalidated_threshold_reason result = com.helium.grpc.mobile.invalidated_threshold_reason.forNumber(reason_);
    return result == null ? com.helium.grpc.mobile.invalidated_threshold_reason.UNRECOGNIZED : result;
  }

  public static final int TIMESTAMP_FIELD_NUMBER = 4;
  private long timestamp_ = 0L;
  /**
   * <pre>
   * Timestamp in milliseconds since unix epoch
   * of when the thresholds were invalidated
   * </pre>
   *
   * <code>uint64 timestamp = 4;</code>
   * @return The timestamp.
   */
  @java.lang.Override
  public long getTimestamp() {
    return timestamp_;
  }

  public static final int CARRIER_PUB_KEY_FIELD_NUMBER = 5;
  private com.google.protobuf.ByteString carrierPubKey_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <pre>
   * pubkey of the carrier identity service
   * </pre>
   *
   * <code>bytes carrier_pub_key = 5;</code>
   * @return The carrierPubKey.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getCarrierPubKey() {
    return carrierPubKey_;
  }

  public static final int SIGNATURE_FIELD_NUMBER = 6;
  private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <pre>
   * signed payload of the carrier identity service
   * </pre>
   *
   * <code>bytes signature = 6;</code>
   * @return The signature.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getSignature() {
    return signature_;
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
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(cbsdId_)) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, cbsdId_);
    }
    if (!hotspotPubkey_.isEmpty()) {
      output.writeBytes(2, hotspotPubkey_);
    }
    if (reason_ != com.helium.grpc.mobile.invalidated_threshold_reason.invalidated_threshold_reason_radio_moved.getNumber()) {
      output.writeEnum(3, reason_);
    }
    if (timestamp_ != 0L) {
      output.writeUInt64(4, timestamp_);
    }
    if (!carrierPubKey_.isEmpty()) {
      output.writeBytes(5, carrierPubKey_);
    }
    if (!signature_.isEmpty()) {
      output.writeBytes(6, signature_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessage.isStringEmpty(cbsdId_)) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, cbsdId_);
    }
    if (!hotspotPubkey_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, hotspotPubkey_);
    }
    if (reason_ != com.helium.grpc.mobile.invalidated_threshold_reason.invalidated_threshold_reason_radio_moved.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, reason_);
    }
    if (timestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, timestamp_);
    }
    if (!carrierPubKey_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(5, carrierPubKey_);
    }
    if (!signature_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(6, signature_);
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
    if (!(obj instanceof com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1)) {
      return super.equals(obj);
    }
    com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 other = (com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1) obj;

    if (!getCbsdId()
        .equals(other.getCbsdId())) return false;
    if (!getHotspotPubkey()
        .equals(other.getHotspotPubkey())) return false;
    if (reason_ != other.reason_) return false;
    if (getTimestamp()
        != other.getTimestamp()) return false;
    if (!getCarrierPubKey()
        .equals(other.getCarrierPubKey())) return false;
    if (!getSignature()
        .equals(other.getSignature())) return false;
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
    hash = (37 * hash) + CBSD_ID_FIELD_NUMBER;
    hash = (53 * hash) + getCbsdId().hashCode();
    hash = (37 * hash) + HOTSPOT_PUBKEY_FIELD_NUMBER;
    hash = (53 * hash) + getHotspotPubkey().hashCode();
    hash = (37 * hash) + REASON_FIELD_NUMBER;
    hash = (53 * hash) + reason_;
    hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimestamp());
    hash = (37 * hash) + CARRIER_PUB_KEY_FIELD_NUMBER;
    hash = (53 * hash) + getCarrierPubKey().hashCode();
    hash = (37 * hash) + SIGNATURE_FIELD_NUMBER;
    hash = (53 * hash) + getSignature().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 prototype) {
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
   * Protobuf type {@code helium.poc_mobile.invalidated_radio_threshold_report_req_v1}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_mobile.invalidated_radio_threshold_report_req_v1)
      com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_invalidated_radio_threshold_report_req_v1_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_invalidated_radio_threshold_report_req_v1_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1.class, com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1.Builder.class);
    }

    // Construct using com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1.newBuilder()
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
      cbsdId_ = "";
      hotspotPubkey_ = com.google.protobuf.ByteString.EMPTY;
      reason_ = 0;
      timestamp_ = 0L;
      carrierPubKey_ = com.google.protobuf.ByteString.EMPTY;
      signature_ = com.google.protobuf.ByteString.EMPTY;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_invalidated_radio_threshold_report_req_v1_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 getDefaultInstanceForType() {
      return com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 build() {
      com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 buildPartial() {
      com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 result = new com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.cbsdId_ = cbsdId_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.hotspotPubkey_ = hotspotPubkey_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.reason_ = reason_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.timestamp_ = timestamp_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.carrierPubKey_ = carrierPubKey_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.signature_ = signature_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1) {
        return mergeFrom((com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 other) {
      if (other == com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1.getDefaultInstance()) return this;
      if (!other.getCbsdId().isEmpty()) {
        cbsdId_ = other.cbsdId_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (other.getHotspotPubkey() != com.google.protobuf.ByteString.EMPTY) {
        setHotspotPubkey(other.getHotspotPubkey());
      }
      if (other.reason_ != 0) {
        setReasonValue(other.getReasonValue());
      }
      if (other.getTimestamp() != 0L) {
        setTimestamp(other.getTimestamp());
      }
      if (other.getCarrierPubKey() != com.google.protobuf.ByteString.EMPTY) {
        setCarrierPubKey(other.getCarrierPubKey());
      }
      if (other.getSignature() != com.google.protobuf.ByteString.EMPTY) {
        setSignature(other.getSignature());
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
              cbsdId_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              hotspotPubkey_ = input.readBytes();
              bitField0_ |= 0x00000002;
              break;
            } // case 18
            case 24: {
              reason_ = input.readEnum();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              timestamp_ = input.readUInt64();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 42: {
              carrierPubKey_ = input.readBytes();
              bitField0_ |= 0x00000010;
              break;
            } // case 42
            case 50: {
              signature_ = input.readBytes();
              bitField0_ |= 0x00000020;
              break;
            } // case 50
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

    private java.lang.Object cbsdId_ = "";
    /**
     * <pre>
     * the id of the radio which thresholds are invalidated
     * </pre>
     *
     * <code>string cbsd_id = 1;</code>
     * @return The cbsdId.
     */
    public java.lang.String getCbsdId() {
      java.lang.Object ref = cbsdId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        cbsdId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * the id of the radio which thresholds are invalidated
     * </pre>
     *
     * <code>string cbsd_id = 1;</code>
     * @return The bytes for cbsdId.
     */
    public com.google.protobuf.ByteString
        getCbsdIdBytes() {
      java.lang.Object ref = cbsdId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        cbsdId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * the id of the radio which thresholds are invalidated
     * </pre>
     *
     * <code>string cbsd_id = 1;</code>
     * @param value The cbsdId to set.
     * @return This builder for chaining.
     */
    public Builder setCbsdId(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      cbsdId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * the id of the radio which thresholds are invalidated
     * </pre>
     *
     * <code>string cbsd_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCbsdId() {
      cbsdId_ = getDefaultInstance().getCbsdId();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * the id of the radio which thresholds are invalidated
     * </pre>
     *
     * <code>string cbsd_id = 1;</code>
     * @param value The bytes for cbsdId to set.
     * @return This builder for chaining.
     */
    public Builder setCbsdIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      cbsdId_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString hotspotPubkey_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * The onchain address of the gateway
     * </pre>
     *
     * <code>bytes hotspot_pubkey = 2;</code>
     * @return The hotspotPubkey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getHotspotPubkey() {
      return hotspotPubkey_;
    }
    /**
     * <pre>
     * The onchain address of the gateway
     * </pre>
     *
     * <code>bytes hotspot_pubkey = 2;</code>
     * @param value The hotspotPubkey to set.
     * @return This builder for chaining.
     */
    public Builder setHotspotPubkey(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      hotspotPubkey_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The onchain address of the gateway
     * </pre>
     *
     * <code>bytes hotspot_pubkey = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearHotspotPubkey() {
      bitField0_ = (bitField0_ & ~0x00000002);
      hotspotPubkey_ = getDefaultInstance().getHotspotPubkey();
      onChanged();
      return this;
    }

    private int reason_ = 0;
    /**
     * <pre>
     * the reason the thresholds are invalidated
     * </pre>
     *
     * <code>.helium.poc_mobile.invalidated_threshold_reason reason = 3;</code>
     * @return The enum numeric value on the wire for reason.
     */
    @java.lang.Override public int getReasonValue() {
      return reason_;
    }
    /**
     * <pre>
     * the reason the thresholds are invalidated
     * </pre>
     *
     * <code>.helium.poc_mobile.invalidated_threshold_reason reason = 3;</code>
     * @param value The enum numeric value on the wire for reason to set.
     * @return This builder for chaining.
     */
    public Builder setReasonValue(int value) {
      reason_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * the reason the thresholds are invalidated
     * </pre>
     *
     * <code>.helium.poc_mobile.invalidated_threshold_reason reason = 3;</code>
     * @return The reason.
     */
    @java.lang.Override
    public com.helium.grpc.mobile.invalidated_threshold_reason getReason() {
      com.helium.grpc.mobile.invalidated_threshold_reason result = com.helium.grpc.mobile.invalidated_threshold_reason.forNumber(reason_);
      return result == null ? com.helium.grpc.mobile.invalidated_threshold_reason.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     * the reason the thresholds are invalidated
     * </pre>
     *
     * <code>.helium.poc_mobile.invalidated_threshold_reason reason = 3;</code>
     * @param value The reason to set.
     * @return This builder for chaining.
     */
    public Builder setReason(com.helium.grpc.mobile.invalidated_threshold_reason value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000004;
      reason_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * the reason the thresholds are invalidated
     * </pre>
     *
     * <code>.helium.poc_mobile.invalidated_threshold_reason reason = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearReason() {
      bitField0_ = (bitField0_ & ~0x00000004);
      reason_ = 0;
      onChanged();
      return this;
    }

    private long timestamp_ ;
    /**
     * <pre>
     * Timestamp in milliseconds since unix epoch
     * of when the thresholds were invalidated
     * </pre>
     *
     * <code>uint64 timestamp = 4;</code>
     * @return The timestamp.
     */
    @java.lang.Override
    public long getTimestamp() {
      return timestamp_;
    }
    /**
     * <pre>
     * Timestamp in milliseconds since unix epoch
     * of when the thresholds were invalidated
     * </pre>
     *
     * <code>uint64 timestamp = 4;</code>
     * @param value The timestamp to set.
     * @return This builder for chaining.
     */
    public Builder setTimestamp(long value) {

      timestamp_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Timestamp in milliseconds since unix epoch
     * of when the thresholds were invalidated
     * </pre>
     *
     * <code>uint64 timestamp = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000008);
      timestamp_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString carrierPubKey_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * pubkey of the carrier identity service
     * </pre>
     *
     * <code>bytes carrier_pub_key = 5;</code>
     * @return The carrierPubKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getCarrierPubKey() {
      return carrierPubKey_;
    }
    /**
     * <pre>
     * pubkey of the carrier identity service
     * </pre>
     *
     * <code>bytes carrier_pub_key = 5;</code>
     * @param value The carrierPubKey to set.
     * @return This builder for chaining.
     */
    public Builder setCarrierPubKey(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      carrierPubKey_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * pubkey of the carrier identity service
     * </pre>
     *
     * <code>bytes carrier_pub_key = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearCarrierPubKey() {
      bitField0_ = (bitField0_ & ~0x00000010);
      carrierPubKey_ = getDefaultInstance().getCarrierPubKey();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <pre>
     * signed payload of the carrier identity service
     * </pre>
     *
     * <code>bytes signature = 6;</code>
     * @return The signature.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSignature() {
      return signature_;
    }
    /**
     * <pre>
     * signed payload of the carrier identity service
     * </pre>
     *
     * <code>bytes signature = 6;</code>
     * @param value The signature to set.
     * @return This builder for chaining.
     */
    public Builder setSignature(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      signature_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * signed payload of the carrier identity service
     * </pre>
     *
     * <code>bytes signature = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearSignature() {
      bitField0_ = (bitField0_ & ~0x00000020);
      signature_ = getDefaultInstance().getSignature();
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helium.poc_mobile.invalidated_radio_threshold_report_req_v1)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_mobile.invalidated_radio_threshold_report_req_v1)
  private static final com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1();
  }

  public static com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<invalidated_radio_threshold_report_req_v1>
      PARSER = new com.google.protobuf.AbstractParser<invalidated_radio_threshold_report_req_v1>() {
    @java.lang.Override
    public invalidated_radio_threshold_report_req_v1 parsePartialFrom(
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

  public static com.google.protobuf.Parser<invalidated_radio_threshold_report_req_v1> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<invalidated_radio_threshold_report_req_v1> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.mobile.invalidated_radio_threshold_report_req_v1 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

