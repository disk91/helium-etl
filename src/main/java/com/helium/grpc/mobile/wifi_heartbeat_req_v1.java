// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf type {@code helium.poc_mobile.wifi_heartbeat_req_v1}
 */
public final class wifi_heartbeat_req_v1 extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:helium.poc_mobile.wifi_heartbeat_req_v1)
    wifi_heartbeat_req_v1OrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      wifi_heartbeat_req_v1.class.getName());
  }
  // Use wifi_heartbeat_req_v1.newBuilder() to construct.
  private wifi_heartbeat_req_v1(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private wifi_heartbeat_req_v1() {
    pubKey_ = com.google.protobuf.ByteString.EMPTY;
    coverageObject_ = com.google.protobuf.ByteString.EMPTY;
    signature_ = com.google.protobuf.ByteString.EMPTY;
    locationSource_ = 0;
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_wifi_heartbeat_req_v1_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_wifi_heartbeat_req_v1_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.mobile.wifi_heartbeat_req_v1.class, com.helium.grpc.mobile.wifi_heartbeat_req_v1.Builder.class);
  }

  public static final int PUB_KEY_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString pubKey_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <pre>
   * Public key of the hotspot
   * </pre>
   *
   * <code>bytes pub_key = 1;</code>
   * @return The pubKey.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getPubKey() {
    return pubKey_;
  }

  public static final int TIMESTAMP_FIELD_NUMBER = 2;
  private long timestamp_ = 0L;
  /**
   * <pre>
   * Timestamp of heartbeat in seconds from unix epoch
   * </pre>
   *
   * <code>uint64 timestamp = 2;</code>
   * @return The timestamp.
   */
  @java.lang.Override
  public long getTimestamp() {
    return timestamp_;
  }

  public static final int LAT_FIELD_NUMBER = 3;
  private double lat_ = 0D;
  /**
   * <code>double lat = 3;</code>
   * @return The lat.
   */
  @java.lang.Override
  public double getLat() {
    return lat_;
  }

  public static final int LON_FIELD_NUMBER = 4;
  private double lon_ = 0D;
  /**
   * <code>double lon = 4;</code>
   * @return The lon.
   */
  @java.lang.Override
  public double getLon() {
    return lon_;
  }

  public static final int LOCATION_VALIDATION_TIMESTAMP_FIELD_NUMBER = 5;
  private long locationValidationTimestamp_ = 0L;
  /**
   * <pre>
   * Timestamp of location validation in seconds from unix epoch
   * </pre>
   *
   * <code>uint64 location_validation_timestamp = 5;</code>
   * @return The locationValidationTimestamp.
   */
  @java.lang.Override
  public long getLocationValidationTimestamp() {
    return locationValidationTimestamp_;
  }

  public static final int OPERATION_MODE_FIELD_NUMBER = 6;
  private boolean operationMode_ = false;
  /**
   * <code>bool operation_mode = 6;</code>
   * @return The operationMode.
   */
  @java.lang.Override
  public boolean getOperationMode() {
    return operationMode_;
  }

  public static final int COVERAGE_OBJECT_FIELD_NUMBER = 7;
  private com.google.protobuf.ByteString coverageObject_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes coverage_object = 7;</code>
   * @return The coverageObject.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getCoverageObject() {
    return coverageObject_;
  }

  public static final int SIGNATURE_FIELD_NUMBER = 8;
  private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes signature = 8;</code>
   * @return The signature.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getSignature() {
    return signature_;
  }

  public static final int LOCATION_SOURCE_FIELD_NUMBER = 9;
  private int locationSource_ = 0;
  /**
   * <code>.helium.poc_mobile.location_source location_source = 9;</code>
   * @return The enum numeric value on the wire for locationSource.
   */
  @java.lang.Override public int getLocationSourceValue() {
    return locationSource_;
  }
  /**
   * <code>.helium.poc_mobile.location_source location_source = 9;</code>
   * @return The locationSource.
   */
  @java.lang.Override public com.helium.grpc.mobile.location_source getLocationSource() {
    com.helium.grpc.mobile.location_source result = com.helium.grpc.mobile.location_source.forNumber(locationSource_);
    return result == null ? com.helium.grpc.mobile.location_source.UNRECOGNIZED : result;
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
      output.writeBytes(1, pubKey_);
    }
    if (timestamp_ != 0L) {
      output.writeUInt64(2, timestamp_);
    }
    if (java.lang.Double.doubleToRawLongBits(lat_) != 0) {
      output.writeDouble(3, lat_);
    }
    if (java.lang.Double.doubleToRawLongBits(lon_) != 0) {
      output.writeDouble(4, lon_);
    }
    if (locationValidationTimestamp_ != 0L) {
      output.writeUInt64(5, locationValidationTimestamp_);
    }
    if (operationMode_ != false) {
      output.writeBool(6, operationMode_);
    }
    if (!coverageObject_.isEmpty()) {
      output.writeBytes(7, coverageObject_);
    }
    if (!signature_.isEmpty()) {
      output.writeBytes(8, signature_);
    }
    if (locationSource_ != com.helium.grpc.mobile.location_source.unknown.getNumber()) {
      output.writeEnum(9, locationSource_);
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
        .computeBytesSize(1, pubKey_);
    }
    if (timestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, timestamp_);
    }
    if (java.lang.Double.doubleToRawLongBits(lat_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(3, lat_);
    }
    if (java.lang.Double.doubleToRawLongBits(lon_) != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(4, lon_);
    }
    if (locationValidationTimestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(5, locationValidationTimestamp_);
    }
    if (operationMode_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(6, operationMode_);
    }
    if (!coverageObject_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(7, coverageObject_);
    }
    if (!signature_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(8, signature_);
    }
    if (locationSource_ != com.helium.grpc.mobile.location_source.unknown.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(9, locationSource_);
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
    if (!(obj instanceof com.helium.grpc.mobile.wifi_heartbeat_req_v1)) {
      return super.equals(obj);
    }
    com.helium.grpc.mobile.wifi_heartbeat_req_v1 other = (com.helium.grpc.mobile.wifi_heartbeat_req_v1) obj;

    if (!getPubKey()
        .equals(other.getPubKey())) return false;
    if (getTimestamp()
        != other.getTimestamp()) return false;
    if (java.lang.Double.doubleToLongBits(getLat())
        != java.lang.Double.doubleToLongBits(
            other.getLat())) return false;
    if (java.lang.Double.doubleToLongBits(getLon())
        != java.lang.Double.doubleToLongBits(
            other.getLon())) return false;
    if (getLocationValidationTimestamp()
        != other.getLocationValidationTimestamp()) return false;
    if (getOperationMode()
        != other.getOperationMode()) return false;
    if (!getCoverageObject()
        .equals(other.getCoverageObject())) return false;
    if (!getSignature()
        .equals(other.getSignature())) return false;
    if (locationSource_ != other.locationSource_) return false;
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
    hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimestamp());
    hash = (37 * hash) + LAT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getLat()));
    hash = (37 * hash) + LON_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getLon()));
    hash = (37 * hash) + LOCATION_VALIDATION_TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getLocationValidationTimestamp());
    hash = (37 * hash) + OPERATION_MODE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getOperationMode());
    hash = (37 * hash) + COVERAGE_OBJECT_FIELD_NUMBER;
    hash = (53 * hash) + getCoverageObject().hashCode();
    hash = (37 * hash) + SIGNATURE_FIELD_NUMBER;
    hash = (53 * hash) + getSignature().hashCode();
    hash = (37 * hash) + LOCATION_SOURCE_FIELD_NUMBER;
    hash = (53 * hash) + locationSource_;
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.mobile.wifi_heartbeat_req_v1 prototype) {
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
   * Protobuf type {@code helium.poc_mobile.wifi_heartbeat_req_v1}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_mobile.wifi_heartbeat_req_v1)
      com.helium.grpc.mobile.wifi_heartbeat_req_v1OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_wifi_heartbeat_req_v1_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_wifi_heartbeat_req_v1_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.mobile.wifi_heartbeat_req_v1.class, com.helium.grpc.mobile.wifi_heartbeat_req_v1.Builder.class);
    }

    // Construct using com.helium.grpc.mobile.wifi_heartbeat_req_v1.newBuilder()
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
      pubKey_ = com.google.protobuf.ByteString.EMPTY;
      timestamp_ = 0L;
      lat_ = 0D;
      lon_ = 0D;
      locationValidationTimestamp_ = 0L;
      operationMode_ = false;
      coverageObject_ = com.google.protobuf.ByteString.EMPTY;
      signature_ = com.google.protobuf.ByteString.EMPTY;
      locationSource_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_wifi_heartbeat_req_v1_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.wifi_heartbeat_req_v1 getDefaultInstanceForType() {
      return com.helium.grpc.mobile.wifi_heartbeat_req_v1.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.mobile.wifi_heartbeat_req_v1 build() {
      com.helium.grpc.mobile.wifi_heartbeat_req_v1 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.wifi_heartbeat_req_v1 buildPartial() {
      com.helium.grpc.mobile.wifi_heartbeat_req_v1 result = new com.helium.grpc.mobile.wifi_heartbeat_req_v1(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.mobile.wifi_heartbeat_req_v1 result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.pubKey_ = pubKey_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.timestamp_ = timestamp_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.lat_ = lat_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.lon_ = lon_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.locationValidationTimestamp_ = locationValidationTimestamp_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.operationMode_ = operationMode_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.coverageObject_ = coverageObject_;
      }
      if (((from_bitField0_ & 0x00000080) != 0)) {
        result.signature_ = signature_;
      }
      if (((from_bitField0_ & 0x00000100) != 0)) {
        result.locationSource_ = locationSource_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.mobile.wifi_heartbeat_req_v1) {
        return mergeFrom((com.helium.grpc.mobile.wifi_heartbeat_req_v1)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.mobile.wifi_heartbeat_req_v1 other) {
      if (other == com.helium.grpc.mobile.wifi_heartbeat_req_v1.getDefaultInstance()) return this;
      if (other.getPubKey() != com.google.protobuf.ByteString.EMPTY) {
        setPubKey(other.getPubKey());
      }
      if (other.getTimestamp() != 0L) {
        setTimestamp(other.getTimestamp());
      }
      if (other.getLat() != 0D) {
        setLat(other.getLat());
      }
      if (other.getLon() != 0D) {
        setLon(other.getLon());
      }
      if (other.getLocationValidationTimestamp() != 0L) {
        setLocationValidationTimestamp(other.getLocationValidationTimestamp());
      }
      if (other.getOperationMode() != false) {
        setOperationMode(other.getOperationMode());
      }
      if (other.getCoverageObject() != com.google.protobuf.ByteString.EMPTY) {
        setCoverageObject(other.getCoverageObject());
      }
      if (other.getSignature() != com.google.protobuf.ByteString.EMPTY) {
        setSignature(other.getSignature());
      }
      if (other.locationSource_ != 0) {
        setLocationSourceValue(other.getLocationSourceValue());
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
              pubKey_ = input.readBytes();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 16: {
              timestamp_ = input.readUInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 25: {
              lat_ = input.readDouble();
              bitField0_ |= 0x00000004;
              break;
            } // case 25
            case 33: {
              lon_ = input.readDouble();
              bitField0_ |= 0x00000008;
              break;
            } // case 33
            case 40: {
              locationValidationTimestamp_ = input.readUInt64();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
            case 48: {
              operationMode_ = input.readBool();
              bitField0_ |= 0x00000020;
              break;
            } // case 48
            case 58: {
              coverageObject_ = input.readBytes();
              bitField0_ |= 0x00000040;
              break;
            } // case 58
            case 66: {
              signature_ = input.readBytes();
              bitField0_ |= 0x00000080;
              break;
            } // case 66
            case 72: {
              locationSource_ = input.readEnum();
              bitField0_ |= 0x00000100;
              break;
            } // case 72
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
     * <pre>
     * Public key of the hotspot
     * </pre>
     *
     * <code>bytes pub_key = 1;</code>
     * @return The pubKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPubKey() {
      return pubKey_;
    }
    /**
     * <pre>
     * Public key of the hotspot
     * </pre>
     *
     * <code>bytes pub_key = 1;</code>
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
     * <pre>
     * Public key of the hotspot
     * </pre>
     *
     * <code>bytes pub_key = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPubKey() {
      bitField0_ = (bitField0_ & ~0x00000001);
      pubKey_ = getDefaultInstance().getPubKey();
      onChanged();
      return this;
    }

    private long timestamp_ ;
    /**
     * <pre>
     * Timestamp of heartbeat in seconds from unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 2;</code>
     * @return The timestamp.
     */
    @java.lang.Override
    public long getTimestamp() {
      return timestamp_;
    }
    /**
     * <pre>
     * Timestamp of heartbeat in seconds from unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 2;</code>
     * @param value The timestamp to set.
     * @return This builder for chaining.
     */
    public Builder setTimestamp(long value) {

      timestamp_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Timestamp of heartbeat in seconds from unix epoch
     * </pre>
     *
     * <code>uint64 timestamp = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000002);
      timestamp_ = 0L;
      onChanged();
      return this;
    }

    private double lat_ ;
    /**
     * <code>double lat = 3;</code>
     * @return The lat.
     */
    @java.lang.Override
    public double getLat() {
      return lat_;
    }
    /**
     * <code>double lat = 3;</code>
     * @param value The lat to set.
     * @return This builder for chaining.
     */
    public Builder setLat(double value) {

      lat_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>double lat = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearLat() {
      bitField0_ = (bitField0_ & ~0x00000004);
      lat_ = 0D;
      onChanged();
      return this;
    }

    private double lon_ ;
    /**
     * <code>double lon = 4;</code>
     * @return The lon.
     */
    @java.lang.Override
    public double getLon() {
      return lon_;
    }
    /**
     * <code>double lon = 4;</code>
     * @param value The lon to set.
     * @return This builder for chaining.
     */
    public Builder setLon(double value) {

      lon_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>double lon = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearLon() {
      bitField0_ = (bitField0_ & ~0x00000008);
      lon_ = 0D;
      onChanged();
      return this;
    }

    private long locationValidationTimestamp_ ;
    /**
     * <pre>
     * Timestamp of location validation in seconds from unix epoch
     * </pre>
     *
     * <code>uint64 location_validation_timestamp = 5;</code>
     * @return The locationValidationTimestamp.
     */
    @java.lang.Override
    public long getLocationValidationTimestamp() {
      return locationValidationTimestamp_;
    }
    /**
     * <pre>
     * Timestamp of location validation in seconds from unix epoch
     * </pre>
     *
     * <code>uint64 location_validation_timestamp = 5;</code>
     * @param value The locationValidationTimestamp to set.
     * @return This builder for chaining.
     */
    public Builder setLocationValidationTimestamp(long value) {

      locationValidationTimestamp_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Timestamp of location validation in seconds from unix epoch
     * </pre>
     *
     * <code>uint64 location_validation_timestamp = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearLocationValidationTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000010);
      locationValidationTimestamp_ = 0L;
      onChanged();
      return this;
    }

    private boolean operationMode_ ;
    /**
     * <code>bool operation_mode = 6;</code>
     * @return The operationMode.
     */
    @java.lang.Override
    public boolean getOperationMode() {
      return operationMode_;
    }
    /**
     * <code>bool operation_mode = 6;</code>
     * @param value The operationMode to set.
     * @return This builder for chaining.
     */
    public Builder setOperationMode(boolean value) {

      operationMode_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <code>bool operation_mode = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearOperationMode() {
      bitField0_ = (bitField0_ & ~0x00000020);
      operationMode_ = false;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString coverageObject_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes coverage_object = 7;</code>
     * @return The coverageObject.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getCoverageObject() {
      return coverageObject_;
    }
    /**
     * <code>bytes coverage_object = 7;</code>
     * @param value The coverageObject to set.
     * @return This builder for chaining.
     */
    public Builder setCoverageObject(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      coverageObject_ = value;
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <code>bytes coverage_object = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearCoverageObject() {
      bitField0_ = (bitField0_ & ~0x00000040);
      coverageObject_ = getDefaultInstance().getCoverageObject();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString signature_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes signature = 8;</code>
     * @return The signature.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getSignature() {
      return signature_;
    }
    /**
     * <code>bytes signature = 8;</code>
     * @param value The signature to set.
     * @return This builder for chaining.
     */
    public Builder setSignature(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      signature_ = value;
      bitField0_ |= 0x00000080;
      onChanged();
      return this;
    }
    /**
     * <code>bytes signature = 8;</code>
     * @return This builder for chaining.
     */
    public Builder clearSignature() {
      bitField0_ = (bitField0_ & ~0x00000080);
      signature_ = getDefaultInstance().getSignature();
      onChanged();
      return this;
    }

    private int locationSource_ = 0;
    /**
     * <code>.helium.poc_mobile.location_source location_source = 9;</code>
     * @return The enum numeric value on the wire for locationSource.
     */
    @java.lang.Override public int getLocationSourceValue() {
      return locationSource_;
    }
    /**
     * <code>.helium.poc_mobile.location_source location_source = 9;</code>
     * @param value The enum numeric value on the wire for locationSource to set.
     * @return This builder for chaining.
     */
    public Builder setLocationSourceValue(int value) {
      locationSource_ = value;
      bitField0_ |= 0x00000100;
      onChanged();
      return this;
    }
    /**
     * <code>.helium.poc_mobile.location_source location_source = 9;</code>
     * @return The locationSource.
     */
    @java.lang.Override
    public com.helium.grpc.mobile.location_source getLocationSource() {
      com.helium.grpc.mobile.location_source result = com.helium.grpc.mobile.location_source.forNumber(locationSource_);
      return result == null ? com.helium.grpc.mobile.location_source.UNRECOGNIZED : result;
    }
    /**
     * <code>.helium.poc_mobile.location_source location_source = 9;</code>
     * @param value The locationSource to set.
     * @return This builder for chaining.
     */
    public Builder setLocationSource(com.helium.grpc.mobile.location_source value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000100;
      locationSource_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.helium.poc_mobile.location_source location_source = 9;</code>
     * @return This builder for chaining.
     */
    public Builder clearLocationSource() {
      bitField0_ = (bitField0_ & ~0x00000100);
      locationSource_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helium.poc_mobile.wifi_heartbeat_req_v1)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_mobile.wifi_heartbeat_req_v1)
  private static final com.helium.grpc.mobile.wifi_heartbeat_req_v1 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.mobile.wifi_heartbeat_req_v1();
  }

  public static com.helium.grpc.mobile.wifi_heartbeat_req_v1 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<wifi_heartbeat_req_v1>
      PARSER = new com.google.protobuf.AbstractParser<wifi_heartbeat_req_v1>() {
    @java.lang.Override
    public wifi_heartbeat_req_v1 parsePartialFrom(
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

  public static com.google.protobuf.Parser<wifi_heartbeat_req_v1> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<wifi_heartbeat_req_v1> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.mobile.wifi_heartbeat_req_v1 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

