// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: mapper.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf type {@code helium.mapper_gps_v1}
 */
public final class mapper_gps_v1 extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:helium.mapper_gps_v1)
    mapper_gps_v1OrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      mapper_gps_v1.class.getName());
  }
  // Use mapper_gps_v1.newBuilder() to construct.
  private mapper_gps_v1(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private mapper_gps_v1() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_gps_v1_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_gps_v1_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.mobile.mapper_gps_v1.class, com.helium.grpc.mobile.mapper_gps_v1.Builder.class);
  }

  public static final int UNIX_TIME_FIELD_NUMBER = 1;
  private int unixTime_ = 0;
  /**
   * <pre>
   * Unix time in seconds
   * </pre>
   *
   * <code>uint32 unix_time = 1;</code>
   * @return The unixTime.
   */
  @java.lang.Override
  public int getUnixTime() {
    return unixTime_;
  }

  public static final int LAT_FIELD_NUMBER = 2;
  private int lat_ = 0;
  /**
   * <pre>
   * Latitude of the current base station in units of 0.25 sec.
   * </pre>
   *
   * <code>int32 lat = 2;</code>
   * @return The lat.
   */
  @java.lang.Override
  public int getLat() {
    return lat_;
  }

  public static final int LON_FIELD_NUMBER = 3;
  private int lon_ = 0;
  /**
   * <pre>
   * Longitude of the current base station in units of 0.25 sec.
   * </pre>
   *
   * <code>int32 lon = 3;</code>
   * @return The lon.
   */
  @java.lang.Override
  public int getLon() {
    return lon_;
  }

  public static final int HDOP_FIELD_NUMBER = 4;
  private int hdop_ = 0;
  /**
   * <pre>
   * Horizontal dilution of position in units of 0.01 HDOP.
   * </pre>
   *
   * <code>uint32 hdop = 4;</code>
   * @return The hdop.
   */
  @java.lang.Override
  public int getHdop() {
    return hdop_;
  }

  public static final int ALTITUDE_FIELD_NUMBER = 5;
  private long altitude_ = 0L;
  /**
   * <pre>
   * Altitude in units of 0.25m above the WGS 84 reference ellipsoid.
   * </pre>
   *
   * <code>int64 altitude = 5;</code>
   * @return The altitude.
   */
  @java.lang.Override
  public long getAltitude() {
    return altitude_;
  }

  public static final int NUM_SATS_FIELD_NUMBER = 6;
  private int numSats_ = 0;
  /**
   * <code>int32 num_sats = 6;</code>
   * @return The numSats.
   */
  @java.lang.Override
  public int getNumSats() {
    return numSats_;
  }

  public static final int SPEED_FIELD_NUMBER = 7;
  private int speed_ = 0;
  /**
   * <pre>
   * Speed in speed in 0.25m/s per second.
   * </pre>
   *
   * <code>uint32 speed = 7;</code>
   * @return The speed.
   */
  @java.lang.Override
  public int getSpeed() {
    return speed_;
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
    if (unixTime_ != 0) {
      output.writeUInt32(1, unixTime_);
    }
    if (lat_ != 0) {
      output.writeInt32(2, lat_);
    }
    if (lon_ != 0) {
      output.writeInt32(3, lon_);
    }
    if (hdop_ != 0) {
      output.writeUInt32(4, hdop_);
    }
    if (altitude_ != 0L) {
      output.writeInt64(5, altitude_);
    }
    if (numSats_ != 0) {
      output.writeInt32(6, numSats_);
    }
    if (speed_ != 0) {
      output.writeUInt32(7, speed_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (unixTime_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(1, unixTime_);
    }
    if (lat_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, lat_);
    }
    if (lon_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, lon_);
    }
    if (hdop_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(4, hdop_);
    }
    if (altitude_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(5, altitude_);
    }
    if (numSats_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, numSats_);
    }
    if (speed_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(7, speed_);
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
    if (!(obj instanceof com.helium.grpc.mobile.mapper_gps_v1)) {
      return super.equals(obj);
    }
    com.helium.grpc.mobile.mapper_gps_v1 other = (com.helium.grpc.mobile.mapper_gps_v1) obj;

    if (getUnixTime()
        != other.getUnixTime()) return false;
    if (getLat()
        != other.getLat()) return false;
    if (getLon()
        != other.getLon()) return false;
    if (getHdop()
        != other.getHdop()) return false;
    if (getAltitude()
        != other.getAltitude()) return false;
    if (getNumSats()
        != other.getNumSats()) return false;
    if (getSpeed()
        != other.getSpeed()) return false;
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
    hash = (37 * hash) + UNIX_TIME_FIELD_NUMBER;
    hash = (53 * hash) + getUnixTime();
    hash = (37 * hash) + LAT_FIELD_NUMBER;
    hash = (53 * hash) + getLat();
    hash = (37 * hash) + LON_FIELD_NUMBER;
    hash = (53 * hash) + getLon();
    hash = (37 * hash) + HDOP_FIELD_NUMBER;
    hash = (53 * hash) + getHdop();
    hash = (37 * hash) + ALTITUDE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAltitude());
    hash = (37 * hash) + NUM_SATS_FIELD_NUMBER;
    hash = (53 * hash) + getNumSats();
    hash = (37 * hash) + SPEED_FIELD_NUMBER;
    hash = (53 * hash) + getSpeed();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.mobile.mapper_gps_v1 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.mobile.mapper_gps_v1 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.mapper_gps_v1 parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.mobile.mapper_gps_v1 prototype) {
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
   * Protobuf type {@code helium.mapper_gps_v1}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.mapper_gps_v1)
      com.helium.grpc.mobile.mapper_gps_v1OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_gps_v1_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_gps_v1_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.mobile.mapper_gps_v1.class, com.helium.grpc.mobile.mapper_gps_v1.Builder.class);
    }

    // Construct using com.helium.grpc.mobile.mapper_gps_v1.newBuilder()
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
      unixTime_ = 0;
      lat_ = 0;
      lon_ = 0;
      hdop_ = 0;
      altitude_ = 0L;
      numSats_ = 0;
      speed_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_gps_v1_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.mapper_gps_v1 getDefaultInstanceForType() {
      return com.helium.grpc.mobile.mapper_gps_v1.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.mobile.mapper_gps_v1 build() {
      com.helium.grpc.mobile.mapper_gps_v1 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.mapper_gps_v1 buildPartial() {
      com.helium.grpc.mobile.mapper_gps_v1 result = new com.helium.grpc.mobile.mapper_gps_v1(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.mobile.mapper_gps_v1 result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.unixTime_ = unixTime_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.lat_ = lat_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.lon_ = lon_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.hdop_ = hdop_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.altitude_ = altitude_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.numSats_ = numSats_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.speed_ = speed_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.mobile.mapper_gps_v1) {
        return mergeFrom((com.helium.grpc.mobile.mapper_gps_v1)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.mobile.mapper_gps_v1 other) {
      if (other == com.helium.grpc.mobile.mapper_gps_v1.getDefaultInstance()) return this;
      if (other.getUnixTime() != 0) {
        setUnixTime(other.getUnixTime());
      }
      if (other.getLat() != 0) {
        setLat(other.getLat());
      }
      if (other.getLon() != 0) {
        setLon(other.getLon());
      }
      if (other.getHdop() != 0) {
        setHdop(other.getHdop());
      }
      if (other.getAltitude() != 0L) {
        setAltitude(other.getAltitude());
      }
      if (other.getNumSats() != 0) {
        setNumSats(other.getNumSats());
      }
      if (other.getSpeed() != 0) {
        setSpeed(other.getSpeed());
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
              unixTime_ = input.readUInt32();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              lat_ = input.readInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              lon_ = input.readInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              hdop_ = input.readUInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 40: {
              altitude_ = input.readInt64();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
            case 48: {
              numSats_ = input.readInt32();
              bitField0_ |= 0x00000020;
              break;
            } // case 48
            case 56: {
              speed_ = input.readUInt32();
              bitField0_ |= 0x00000040;
              break;
            } // case 56
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

    private int unixTime_ ;
    /**
     * <pre>
     * Unix time in seconds
     * </pre>
     *
     * <code>uint32 unix_time = 1;</code>
     * @return The unixTime.
     */
    @java.lang.Override
    public int getUnixTime() {
      return unixTime_;
    }
    /**
     * <pre>
     * Unix time in seconds
     * </pre>
     *
     * <code>uint32 unix_time = 1;</code>
     * @param value The unixTime to set.
     * @return This builder for chaining.
     */
    public Builder setUnixTime(int value) {

      unixTime_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Unix time in seconds
     * </pre>
     *
     * <code>uint32 unix_time = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearUnixTime() {
      bitField0_ = (bitField0_ & ~0x00000001);
      unixTime_ = 0;
      onChanged();
      return this;
    }

    private int lat_ ;
    /**
     * <pre>
     * Latitude of the current base station in units of 0.25 sec.
     * </pre>
     *
     * <code>int32 lat = 2;</code>
     * @return The lat.
     */
    @java.lang.Override
    public int getLat() {
      return lat_;
    }
    /**
     * <pre>
     * Latitude of the current base station in units of 0.25 sec.
     * </pre>
     *
     * <code>int32 lat = 2;</code>
     * @param value The lat to set.
     * @return This builder for chaining.
     */
    public Builder setLat(int value) {

      lat_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Latitude of the current base station in units of 0.25 sec.
     * </pre>
     *
     * <code>int32 lat = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearLat() {
      bitField0_ = (bitField0_ & ~0x00000002);
      lat_ = 0;
      onChanged();
      return this;
    }

    private int lon_ ;
    /**
     * <pre>
     * Longitude of the current base station in units of 0.25 sec.
     * </pre>
     *
     * <code>int32 lon = 3;</code>
     * @return The lon.
     */
    @java.lang.Override
    public int getLon() {
      return lon_;
    }
    /**
     * <pre>
     * Longitude of the current base station in units of 0.25 sec.
     * </pre>
     *
     * <code>int32 lon = 3;</code>
     * @param value The lon to set.
     * @return This builder for chaining.
     */
    public Builder setLon(int value) {

      lon_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Longitude of the current base station in units of 0.25 sec.
     * </pre>
     *
     * <code>int32 lon = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearLon() {
      bitField0_ = (bitField0_ & ~0x00000004);
      lon_ = 0;
      onChanged();
      return this;
    }

    private int hdop_ ;
    /**
     * <pre>
     * Horizontal dilution of position in units of 0.01 HDOP.
     * </pre>
     *
     * <code>uint32 hdop = 4;</code>
     * @return The hdop.
     */
    @java.lang.Override
    public int getHdop() {
      return hdop_;
    }
    /**
     * <pre>
     * Horizontal dilution of position in units of 0.01 HDOP.
     * </pre>
     *
     * <code>uint32 hdop = 4;</code>
     * @param value The hdop to set.
     * @return This builder for chaining.
     */
    public Builder setHdop(int value) {

      hdop_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Horizontal dilution of position in units of 0.01 HDOP.
     * </pre>
     *
     * <code>uint32 hdop = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearHdop() {
      bitField0_ = (bitField0_ & ~0x00000008);
      hdop_ = 0;
      onChanged();
      return this;
    }

    private long altitude_ ;
    /**
     * <pre>
     * Altitude in units of 0.25m above the WGS 84 reference ellipsoid.
     * </pre>
     *
     * <code>int64 altitude = 5;</code>
     * @return The altitude.
     */
    @java.lang.Override
    public long getAltitude() {
      return altitude_;
    }
    /**
     * <pre>
     * Altitude in units of 0.25m above the WGS 84 reference ellipsoid.
     * </pre>
     *
     * <code>int64 altitude = 5;</code>
     * @param value The altitude to set.
     * @return This builder for chaining.
     */
    public Builder setAltitude(long value) {

      altitude_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Altitude in units of 0.25m above the WGS 84 reference ellipsoid.
     * </pre>
     *
     * <code>int64 altitude = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearAltitude() {
      bitField0_ = (bitField0_ & ~0x00000010);
      altitude_ = 0L;
      onChanged();
      return this;
    }

    private int numSats_ ;
    /**
     * <code>int32 num_sats = 6;</code>
     * @return The numSats.
     */
    @java.lang.Override
    public int getNumSats() {
      return numSats_;
    }
    /**
     * <code>int32 num_sats = 6;</code>
     * @param value The numSats to set.
     * @return This builder for chaining.
     */
    public Builder setNumSats(int value) {

      numSats_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <code>int32 num_sats = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearNumSats() {
      bitField0_ = (bitField0_ & ~0x00000020);
      numSats_ = 0;
      onChanged();
      return this;
    }

    private int speed_ ;
    /**
     * <pre>
     * Speed in speed in 0.25m/s per second.
     * </pre>
     *
     * <code>uint32 speed = 7;</code>
     * @return The speed.
     */
    @java.lang.Override
    public int getSpeed() {
      return speed_;
    }
    /**
     * <pre>
     * Speed in speed in 0.25m/s per second.
     * </pre>
     *
     * <code>uint32 speed = 7;</code>
     * @param value The speed to set.
     * @return This builder for chaining.
     */
    public Builder setSpeed(int value) {

      speed_ = value;
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Speed in speed in 0.25m/s per second.
     * </pre>
     *
     * <code>uint32 speed = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearSpeed() {
      bitField0_ = (bitField0_ & ~0x00000040);
      speed_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helium.mapper_gps_v1)
  }

  // @@protoc_insertion_point(class_scope:helium.mapper_gps_v1)
  private static final com.helium.grpc.mobile.mapper_gps_v1 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.mobile.mapper_gps_v1();
  }

  public static com.helium.grpc.mobile.mapper_gps_v1 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<mapper_gps_v1>
      PARSER = new com.google.protobuf.AbstractParser<mapper_gps_v1>() {
    @java.lang.Override
    public mapper_gps_v1 parsePartialFrom(
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

  public static com.google.protobuf.Parser<mapper_gps_v1> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<mapper_gps_v1> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.mobile.mapper_gps_v1 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

