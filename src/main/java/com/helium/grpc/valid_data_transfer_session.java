// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/packet_verifier.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc;

/**
 * Protobuf type {@code helium.packet_verifier.valid_data_transfer_session}
 */
public final class valid_data_transfer_session extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:helium.packet_verifier.valid_data_transfer_session)
    valid_data_transfer_sessionOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      valid_data_transfer_session.class.getName());
  }
  // Use valid_data_transfer_session.newBuilder() to construct.
  private valid_data_transfer_session(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private valid_data_transfer_session() {
    pubKey_ = com.google.protobuf.ByteString.EMPTY;
    payer_ = com.google.protobuf.ByteString.EMPTY;
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.PacketVerifier.internal_static_helium_packet_verifier_valid_data_transfer_session_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.PacketVerifier.internal_static_helium_packet_verifier_valid_data_transfer_session_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.valid_data_transfer_session.class, com.helium.grpc.valid_data_transfer_session.Builder.class);
  }

  public static final int PUB_KEY_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString pubKey_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes pub_key = 1;</code>
   * @return The pubKey.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getPubKey() {
    return pubKey_;
  }

  public static final int UPLOAD_BYTES_FIELD_NUMBER = 2;
  private long uploadBytes_ = 0L;
  /**
   * <code>uint64 upload_bytes = 2;</code>
   * @return The uploadBytes.
   */
  @java.lang.Override
  public long getUploadBytes() {
    return uploadBytes_;
  }

  public static final int DOWNLOAD_BYTES_FIELD_NUMBER = 3;
  private long downloadBytes_ = 0L;
  /**
   * <code>uint64 download_bytes = 3;</code>
   * @return The downloadBytes.
   */
  @java.lang.Override
  public long getDownloadBytes() {
    return downloadBytes_;
  }

  public static final int NUM_DCS_FIELD_NUMBER = 4;
  private long numDcs_ = 0L;
  /**
   * <code>uint64 num_dcs = 4;</code>
   * @return The numDcs.
   */
  @java.lang.Override
  public long getNumDcs() {
    return numDcs_;
  }

  public static final int PAYER_FIELD_NUMBER = 5;
  private com.google.protobuf.ByteString payer_ = com.google.protobuf.ByteString.EMPTY;
  /**
   * <code>bytes payer = 5;</code>
   * @return The payer.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getPayer() {
    return payer_;
  }

  public static final int FIRST_TIMESTAMP_FIELD_NUMBER = 6;
  private long firstTimestamp_ = 0L;
  /**
   * <pre>
   * Timestamp in millis of the first ingest file we found a data transfer
   * session in
   * </pre>
   *
   * <code>uint64 first_timestamp = 6;</code>
   * @return The firstTimestamp.
   */
  @java.lang.Override
  public long getFirstTimestamp() {
    return firstTimestamp_;
  }

  public static final int LAST_TIMESTAMP_FIELD_NUMBER = 7;
  private long lastTimestamp_ = 0L;
  /**
   * <pre>
   * Timestamp in millis of the last ingest file we found a data transfer
   * session in
   * </pre>
   *
   * <code>uint64 last_timestamp = 7;</code>
   * @return The lastTimestamp.
   */
  @java.lang.Override
  public long getLastTimestamp() {
    return lastTimestamp_;
  }

  public static final int REWARDABLE_BYTES_FIELD_NUMBER = 8;
  private long rewardableBytes_ = 0L;
  /**
   * <code>uint64 rewardable_bytes = 8;</code>
   * @return The rewardableBytes.
   */
  @java.lang.Override
  public long getRewardableBytes() {
    return rewardableBytes_;
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
    if (uploadBytes_ != 0L) {
      output.writeUInt64(2, uploadBytes_);
    }
    if (downloadBytes_ != 0L) {
      output.writeUInt64(3, downloadBytes_);
    }
    if (numDcs_ != 0L) {
      output.writeUInt64(4, numDcs_);
    }
    if (!payer_.isEmpty()) {
      output.writeBytes(5, payer_);
    }
    if (firstTimestamp_ != 0L) {
      output.writeUInt64(6, firstTimestamp_);
    }
    if (lastTimestamp_ != 0L) {
      output.writeUInt64(7, lastTimestamp_);
    }
    if (rewardableBytes_ != 0L) {
      output.writeUInt64(8, rewardableBytes_);
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
    if (uploadBytes_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(2, uploadBytes_);
    }
    if (downloadBytes_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(3, downloadBytes_);
    }
    if (numDcs_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(4, numDcs_);
    }
    if (!payer_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(5, payer_);
    }
    if (firstTimestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(6, firstTimestamp_);
    }
    if (lastTimestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(7, lastTimestamp_);
    }
    if (rewardableBytes_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(8, rewardableBytes_);
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
    if (!(obj instanceof com.helium.grpc.valid_data_transfer_session)) {
      return super.equals(obj);
    }
    com.helium.grpc.valid_data_transfer_session other = (com.helium.grpc.valid_data_transfer_session) obj;

    if (!getPubKey()
        .equals(other.getPubKey())) return false;
    if (getUploadBytes()
        != other.getUploadBytes()) return false;
    if (getDownloadBytes()
        != other.getDownloadBytes()) return false;
    if (getNumDcs()
        != other.getNumDcs()) return false;
    if (!getPayer()
        .equals(other.getPayer())) return false;
    if (getFirstTimestamp()
        != other.getFirstTimestamp()) return false;
    if (getLastTimestamp()
        != other.getLastTimestamp()) return false;
    if (getRewardableBytes()
        != other.getRewardableBytes()) return false;
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
    hash = (37 * hash) + UPLOAD_BYTES_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUploadBytes());
    hash = (37 * hash) + DOWNLOAD_BYTES_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getDownloadBytes());
    hash = (37 * hash) + NUM_DCS_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getNumDcs());
    hash = (37 * hash) + PAYER_FIELD_NUMBER;
    hash = (53 * hash) + getPayer().hashCode();
    hash = (37 * hash) + FIRST_TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFirstTimestamp());
    hash = (37 * hash) + LAST_TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getLastTimestamp());
    hash = (37 * hash) + REWARDABLE_BYTES_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRewardableBytes());
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.valid_data_transfer_session parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.valid_data_transfer_session parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.valid_data_transfer_session parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.valid_data_transfer_session parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.valid_data_transfer_session prototype) {
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
   * Protobuf type {@code helium.packet_verifier.valid_data_transfer_session}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.packet_verifier.valid_data_transfer_session)
      com.helium.grpc.valid_data_transfer_sessionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.PacketVerifier.internal_static_helium_packet_verifier_valid_data_transfer_session_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.PacketVerifier.internal_static_helium_packet_verifier_valid_data_transfer_session_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.valid_data_transfer_session.class, com.helium.grpc.valid_data_transfer_session.Builder.class);
    }

    // Construct using com.helium.grpc.valid_data_transfer_session.newBuilder()
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
      uploadBytes_ = 0L;
      downloadBytes_ = 0L;
      numDcs_ = 0L;
      payer_ = com.google.protobuf.ByteString.EMPTY;
      firstTimestamp_ = 0L;
      lastTimestamp_ = 0L;
      rewardableBytes_ = 0L;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.PacketVerifier.internal_static_helium_packet_verifier_valid_data_transfer_session_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.valid_data_transfer_session getDefaultInstanceForType() {
      return com.helium.grpc.valid_data_transfer_session.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.valid_data_transfer_session build() {
      com.helium.grpc.valid_data_transfer_session result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.valid_data_transfer_session buildPartial() {
      com.helium.grpc.valid_data_transfer_session result = new com.helium.grpc.valid_data_transfer_session(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.valid_data_transfer_session result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.pubKey_ = pubKey_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.uploadBytes_ = uploadBytes_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.downloadBytes_ = downloadBytes_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.numDcs_ = numDcs_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.payer_ = payer_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.firstTimestamp_ = firstTimestamp_;
      }
      if (((from_bitField0_ & 0x00000040) != 0)) {
        result.lastTimestamp_ = lastTimestamp_;
      }
      if (((from_bitField0_ & 0x00000080) != 0)) {
        result.rewardableBytes_ = rewardableBytes_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.valid_data_transfer_session) {
        return mergeFrom((com.helium.grpc.valid_data_transfer_session)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.valid_data_transfer_session other) {
      if (other == com.helium.grpc.valid_data_transfer_session.getDefaultInstance()) return this;
      if (other.getPubKey() != com.google.protobuf.ByteString.EMPTY) {
        setPubKey(other.getPubKey());
      }
      if (other.getUploadBytes() != 0L) {
        setUploadBytes(other.getUploadBytes());
      }
      if (other.getDownloadBytes() != 0L) {
        setDownloadBytes(other.getDownloadBytes());
      }
      if (other.getNumDcs() != 0L) {
        setNumDcs(other.getNumDcs());
      }
      if (other.getPayer() != com.google.protobuf.ByteString.EMPTY) {
        setPayer(other.getPayer());
      }
      if (other.getFirstTimestamp() != 0L) {
        setFirstTimestamp(other.getFirstTimestamp());
      }
      if (other.getLastTimestamp() != 0L) {
        setLastTimestamp(other.getLastTimestamp());
      }
      if (other.getRewardableBytes() != 0L) {
        setRewardableBytes(other.getRewardableBytes());
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
              uploadBytes_ = input.readUInt64();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              downloadBytes_ = input.readUInt64();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              numDcs_ = input.readUInt64();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 42: {
              payer_ = input.readBytes();
              bitField0_ |= 0x00000010;
              break;
            } // case 42
            case 48: {
              firstTimestamp_ = input.readUInt64();
              bitField0_ |= 0x00000020;
              break;
            } // case 48
            case 56: {
              lastTimestamp_ = input.readUInt64();
              bitField0_ |= 0x00000040;
              break;
            } // case 56
            case 64: {
              rewardableBytes_ = input.readUInt64();
              bitField0_ |= 0x00000080;
              break;
            } // case 64
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
     * <code>bytes pub_key = 1;</code>
     * @return The pubKey.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPubKey() {
      return pubKey_;
    }
    /**
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
     * <code>bytes pub_key = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPubKey() {
      bitField0_ = (bitField0_ & ~0x00000001);
      pubKey_ = getDefaultInstance().getPubKey();
      onChanged();
      return this;
    }

    private long uploadBytes_ ;
    /**
     * <code>uint64 upload_bytes = 2;</code>
     * @return The uploadBytes.
     */
    @java.lang.Override
    public long getUploadBytes() {
      return uploadBytes_;
    }
    /**
     * <code>uint64 upload_bytes = 2;</code>
     * @param value The uploadBytes to set.
     * @return This builder for chaining.
     */
    public Builder setUploadBytes(long value) {

      uploadBytes_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 upload_bytes = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearUploadBytes() {
      bitField0_ = (bitField0_ & ~0x00000002);
      uploadBytes_ = 0L;
      onChanged();
      return this;
    }

    private long downloadBytes_ ;
    /**
     * <code>uint64 download_bytes = 3;</code>
     * @return The downloadBytes.
     */
    @java.lang.Override
    public long getDownloadBytes() {
      return downloadBytes_;
    }
    /**
     * <code>uint64 download_bytes = 3;</code>
     * @param value The downloadBytes to set.
     * @return This builder for chaining.
     */
    public Builder setDownloadBytes(long value) {

      downloadBytes_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 download_bytes = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearDownloadBytes() {
      bitField0_ = (bitField0_ & ~0x00000004);
      downloadBytes_ = 0L;
      onChanged();
      return this;
    }

    private long numDcs_ ;
    /**
     * <code>uint64 num_dcs = 4;</code>
     * @return The numDcs.
     */
    @java.lang.Override
    public long getNumDcs() {
      return numDcs_;
    }
    /**
     * <code>uint64 num_dcs = 4;</code>
     * @param value The numDcs to set.
     * @return This builder for chaining.
     */
    public Builder setNumDcs(long value) {

      numDcs_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 num_dcs = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearNumDcs() {
      bitField0_ = (bitField0_ & ~0x00000008);
      numDcs_ = 0L;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString payer_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes payer = 5;</code>
     * @return The payer.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getPayer() {
      return payer_;
    }
    /**
     * <code>bytes payer = 5;</code>
     * @param value The payer to set.
     * @return This builder for chaining.
     */
    public Builder setPayer(com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      payer_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <code>bytes payer = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearPayer() {
      bitField0_ = (bitField0_ & ~0x00000010);
      payer_ = getDefaultInstance().getPayer();
      onChanged();
      return this;
    }

    private long firstTimestamp_ ;
    /**
     * <pre>
     * Timestamp in millis of the first ingest file we found a data transfer
     * session in
     * </pre>
     *
     * <code>uint64 first_timestamp = 6;</code>
     * @return The firstTimestamp.
     */
    @java.lang.Override
    public long getFirstTimestamp() {
      return firstTimestamp_;
    }
    /**
     * <pre>
     * Timestamp in millis of the first ingest file we found a data transfer
     * session in
     * </pre>
     *
     * <code>uint64 first_timestamp = 6;</code>
     * @param value The firstTimestamp to set.
     * @return This builder for chaining.
     */
    public Builder setFirstTimestamp(long value) {

      firstTimestamp_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Timestamp in millis of the first ingest file we found a data transfer
     * session in
     * </pre>
     *
     * <code>uint64 first_timestamp = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearFirstTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000020);
      firstTimestamp_ = 0L;
      onChanged();
      return this;
    }

    private long lastTimestamp_ ;
    /**
     * <pre>
     * Timestamp in millis of the last ingest file we found a data transfer
     * session in
     * </pre>
     *
     * <code>uint64 last_timestamp = 7;</code>
     * @return The lastTimestamp.
     */
    @java.lang.Override
    public long getLastTimestamp() {
      return lastTimestamp_;
    }
    /**
     * <pre>
     * Timestamp in millis of the last ingest file we found a data transfer
     * session in
     * </pre>
     *
     * <code>uint64 last_timestamp = 7;</code>
     * @param value The lastTimestamp to set.
     * @return This builder for chaining.
     */
    public Builder setLastTimestamp(long value) {

      lastTimestamp_ = value;
      bitField0_ |= 0x00000040;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Timestamp in millis of the last ingest file we found a data transfer
     * session in
     * </pre>
     *
     * <code>uint64 last_timestamp = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearLastTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000040);
      lastTimestamp_ = 0L;
      onChanged();
      return this;
    }

    private long rewardableBytes_ ;
    /**
     * <code>uint64 rewardable_bytes = 8;</code>
     * @return The rewardableBytes.
     */
    @java.lang.Override
    public long getRewardableBytes() {
      return rewardableBytes_;
    }
    /**
     * <code>uint64 rewardable_bytes = 8;</code>
     * @param value The rewardableBytes to set.
     * @return This builder for chaining.
     */
    public Builder setRewardableBytes(long value) {

      rewardableBytes_ = value;
      bitField0_ |= 0x00000080;
      onChanged();
      return this;
    }
    /**
     * <code>uint64 rewardable_bytes = 8;</code>
     * @return This builder for chaining.
     */
    public Builder clearRewardableBytes() {
      bitField0_ = (bitField0_ & ~0x00000080);
      rewardableBytes_ = 0L;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helium.packet_verifier.valid_data_transfer_session)
  }

  // @@protoc_insertion_point(class_scope:helium.packet_verifier.valid_data_transfer_session)
  private static final com.helium.grpc.valid_data_transfer_session DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.valid_data_transfer_session();
  }

  public static com.helium.grpc.valid_data_transfer_session getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<valid_data_transfer_session>
      PARSER = new com.google.protobuf.AbstractParser<valid_data_transfer_session>() {
    @java.lang.Override
    public valid_data_transfer_session parsePartialFrom(
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

  public static com.google.protobuf.Parser<valid_data_transfer_session> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<valid_data_transfer_session> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.valid_data_transfer_session getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

