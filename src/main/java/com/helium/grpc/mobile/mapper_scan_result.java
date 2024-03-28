// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mapper.proto

// Protobuf Java Version: 3.25.1
package com.helium.grpc.mobile;

/**
 * Protobuf type {@code helium.mapper_scan_result}
 */
public final class mapper_scan_result extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:helium.mapper_scan_result)
    mapper_scan_resultOrBuilder {
private static final long serialVersionUID = 0L;
  // Use mapper_scan_result.newBuilder() to construct.
  private mapper_scan_result(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private mapper_scan_result() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new mapper_scan_result();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_scan_result_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_scan_result_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.mobile.mapper_scan_result.class, com.helium.grpc.mobile.mapper_scan_result.Builder.class);
  }

  public static final int CID_FIELD_NUMBER = 1;
  private long cid_ = 0L;
  /**
   * <pre>
   * 28-bit (UMTS, LTE) or 36-bit (5G NR)
   * </pre>
   *
   * <code>uint64 cid = 1;</code>
   * @return The cid.
   */
  @java.lang.Override
  public long getCid() {
    return cid_;
  }

  public static final int PLMN_FIELD_NUMBER = 2;
  private int plmn_ = 0;
  /**
   * <pre>
   * PLMN = (MCC &lt;&lt; 16) | MNC
   * </pre>
   *
   * <code>uint32 plmn = 2;</code>
   * @return The plmn.
   */
  @java.lang.Override
  public int getPlmn() {
    return plmn_;
  }

  public static final int FCN_FIELD_NUMBER = 3;
  private int fcn_ = 0;
  /**
   * <pre>
   * EARFCN or UARFCN
   * </pre>
   *
   * <code>uint32 fcn = 3;</code>
   * @return The fcn.
   */
  @java.lang.Override
  public int getFcn() {
    return fcn_;
  }

  public static final int PCI_FIELD_NUMBER = 4;
  private int pci_ = 0;
  /**
   * <code>uint32 pci = 4;</code>
   * @return The pci.
   */
  @java.lang.Override
  public int getPci() {
    return pci_;
  }

  public static final int RSRP_FIELD_NUMBER = 5;
  private int rsrp_ = 0;
  /**
   * <pre>
   * RSRQ in units of 0.1 dB
   * </pre>
   *
   * <code>int32 rsrp = 5;</code>
   * @return The rsrp.
   */
  @java.lang.Override
  public int getRsrp() {
    return rsrp_;
  }

  public static final int RSRQ_FIELD_NUMBER = 6;
  private int rsrq_ = 0;
  /**
   * <pre>
   * RSRP in units of 0.1 dBm
   * </pre>
   *
   * <code>int32 rsrq = 6;</code>
   * @return The rsrq.
   */
  @java.lang.Override
  public int getRsrq() {
    return rsrq_;
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
    if (cid_ != 0L) {
      output.writeUInt64(1, cid_);
    }
    if (plmn_ != 0) {
      output.writeUInt32(2, plmn_);
    }
    if (fcn_ != 0) {
      output.writeUInt32(3, fcn_);
    }
    if (pci_ != 0) {
      output.writeUInt32(4, pci_);
    }
    if (rsrp_ != 0) {
      output.writeInt32(5, rsrp_);
    }
    if (rsrq_ != 0) {
      output.writeInt32(6, rsrq_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (cid_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, cid_);
    }
    if (plmn_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(2, plmn_);
    }
    if (fcn_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(3, fcn_);
    }
    if (pci_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt32Size(4, pci_);
    }
    if (rsrp_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, rsrp_);
    }
    if (rsrq_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, rsrq_);
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
    if (!(obj instanceof com.helium.grpc.mobile.mapper_scan_result)) {
      return super.equals(obj);
    }
    com.helium.grpc.mobile.mapper_scan_result other = (com.helium.grpc.mobile.mapper_scan_result) obj;

    if (getCid()
        != other.getCid()) return false;
    if (getPlmn()
        != other.getPlmn()) return false;
    if (getFcn()
        != other.getFcn()) return false;
    if (getPci()
        != other.getPci()) return false;
    if (getRsrp()
        != other.getRsrp()) return false;
    if (getRsrq()
        != other.getRsrq()) return false;
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
    hash = (37 * hash) + CID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getCid());
    hash = (37 * hash) + PLMN_FIELD_NUMBER;
    hash = (53 * hash) + getPlmn();
    hash = (37 * hash) + FCN_FIELD_NUMBER;
    hash = (53 * hash) + getFcn();
    hash = (37 * hash) + PCI_FIELD_NUMBER;
    hash = (53 * hash) + getPci();
    hash = (37 * hash) + RSRP_FIELD_NUMBER;
    hash = (53 * hash) + getRsrp();
    hash = (37 * hash) + RSRQ_FIELD_NUMBER;
    hash = (53 * hash) + getRsrq();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.mobile.mapper_scan_result parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.mobile.mapper_scan_result parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.mapper_scan_result parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.mobile.mapper_scan_result prototype) {
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
   * Protobuf type {@code helium.mapper_scan_result}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.mapper_scan_result)
      com.helium.grpc.mobile.mapper_scan_resultOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_scan_result_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_scan_result_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.mobile.mapper_scan_result.class, com.helium.grpc.mobile.mapper_scan_result.Builder.class);
    }

    // Construct using com.helium.grpc.mobile.mapper_scan_result.newBuilder()
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
      cid_ = 0L;
      plmn_ = 0;
      fcn_ = 0;
      pci_ = 0;
      rsrp_ = 0;
      rsrq_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.mobile.MapperClass.internal_static_helium_mapper_scan_result_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.mapper_scan_result getDefaultInstanceForType() {
      return com.helium.grpc.mobile.mapper_scan_result.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.mobile.mapper_scan_result build() {
      com.helium.grpc.mobile.mapper_scan_result result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.mapper_scan_result buildPartial() {
      com.helium.grpc.mobile.mapper_scan_result result = new com.helium.grpc.mobile.mapper_scan_result(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.mobile.mapper_scan_result result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.cid_ = cid_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.plmn_ = plmn_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.fcn_ = fcn_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.pci_ = pci_;
      }
      if (((from_bitField0_ & 0x00000010) != 0)) {
        result.rsrp_ = rsrp_;
      }
      if (((from_bitField0_ & 0x00000020) != 0)) {
        result.rsrq_ = rsrq_;
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
      if (other instanceof com.helium.grpc.mobile.mapper_scan_result) {
        return mergeFrom((com.helium.grpc.mobile.mapper_scan_result)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.mobile.mapper_scan_result other) {
      if (other == com.helium.grpc.mobile.mapper_scan_result.getDefaultInstance()) return this;
      if (other.getCid() != 0L) {
        setCid(other.getCid());
      }
      if (other.getPlmn() != 0) {
        setPlmn(other.getPlmn());
      }
      if (other.getFcn() != 0) {
        setFcn(other.getFcn());
      }
      if (other.getPci() != 0) {
        setPci(other.getPci());
      }
      if (other.getRsrp() != 0) {
        setRsrp(other.getRsrp());
      }
      if (other.getRsrq() != 0) {
        setRsrq(other.getRsrq());
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
              cid_ = input.readUInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 16: {
              plmn_ = input.readUInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 24: {
              fcn_ = input.readUInt32();
              bitField0_ |= 0x00000004;
              break;
            } // case 24
            case 32: {
              pci_ = input.readUInt32();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            case 40: {
              rsrp_ = input.readInt32();
              bitField0_ |= 0x00000010;
              break;
            } // case 40
            case 48: {
              rsrq_ = input.readInt32();
              bitField0_ |= 0x00000020;
              break;
            } // case 48
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

    private long cid_ ;
    /**
     * <pre>
     * 28-bit (UMTS, LTE) or 36-bit (5G NR)
     * </pre>
     *
     * <code>uint64 cid = 1;</code>
     * @return The cid.
     */
    @java.lang.Override
    public long getCid() {
      return cid_;
    }
    /**
     * <pre>
     * 28-bit (UMTS, LTE) or 36-bit (5G NR)
     * </pre>
     *
     * <code>uint64 cid = 1;</code>
     * @param value The cid to set.
     * @return This builder for chaining.
     */
    public Builder setCid(long value) {

      cid_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 28-bit (UMTS, LTE) or 36-bit (5G NR)
     * </pre>
     *
     * <code>uint64 cid = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearCid() {
      bitField0_ = (bitField0_ & ~0x00000001);
      cid_ = 0L;
      onChanged();
      return this;
    }

    private int plmn_ ;
    /**
     * <pre>
     * PLMN = (MCC &lt;&lt; 16) | MNC
     * </pre>
     *
     * <code>uint32 plmn = 2;</code>
     * @return The plmn.
     */
    @java.lang.Override
    public int getPlmn() {
      return plmn_;
    }
    /**
     * <pre>
     * PLMN = (MCC &lt;&lt; 16) | MNC
     * </pre>
     *
     * <code>uint32 plmn = 2;</code>
     * @param value The plmn to set.
     * @return This builder for chaining.
     */
    public Builder setPlmn(int value) {

      plmn_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * PLMN = (MCC &lt;&lt; 16) | MNC
     * </pre>
     *
     * <code>uint32 plmn = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPlmn() {
      bitField0_ = (bitField0_ & ~0x00000002);
      plmn_ = 0;
      onChanged();
      return this;
    }

    private int fcn_ ;
    /**
     * <pre>
     * EARFCN or UARFCN
     * </pre>
     *
     * <code>uint32 fcn = 3;</code>
     * @return The fcn.
     */
    @java.lang.Override
    public int getFcn() {
      return fcn_;
    }
    /**
     * <pre>
     * EARFCN or UARFCN
     * </pre>
     *
     * <code>uint32 fcn = 3;</code>
     * @param value The fcn to set.
     * @return This builder for chaining.
     */
    public Builder setFcn(int value) {

      fcn_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * EARFCN or UARFCN
     * </pre>
     *
     * <code>uint32 fcn = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearFcn() {
      bitField0_ = (bitField0_ & ~0x00000004);
      fcn_ = 0;
      onChanged();
      return this;
    }

    private int pci_ ;
    /**
     * <code>uint32 pci = 4;</code>
     * @return The pci.
     */
    @java.lang.Override
    public int getPci() {
      return pci_;
    }
    /**
     * <code>uint32 pci = 4;</code>
     * @param value The pci to set.
     * @return This builder for chaining.
     */
    public Builder setPci(int value) {

      pci_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <code>uint32 pci = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearPci() {
      bitField0_ = (bitField0_ & ~0x00000008);
      pci_ = 0;
      onChanged();
      return this;
    }

    private int rsrp_ ;
    /**
     * <pre>
     * RSRQ in units of 0.1 dB
     * </pre>
     *
     * <code>int32 rsrp = 5;</code>
     * @return The rsrp.
     */
    @java.lang.Override
    public int getRsrp() {
      return rsrp_;
    }
    /**
     * <pre>
     * RSRQ in units of 0.1 dB
     * </pre>
     *
     * <code>int32 rsrp = 5;</code>
     * @param value The rsrp to set.
     * @return This builder for chaining.
     */
    public Builder setRsrp(int value) {

      rsrp_ = value;
      bitField0_ |= 0x00000010;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * RSRQ in units of 0.1 dB
     * </pre>
     *
     * <code>int32 rsrp = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearRsrp() {
      bitField0_ = (bitField0_ & ~0x00000010);
      rsrp_ = 0;
      onChanged();
      return this;
    }

    private int rsrq_ ;
    /**
     * <pre>
     * RSRP in units of 0.1 dBm
     * </pre>
     *
     * <code>int32 rsrq = 6;</code>
     * @return The rsrq.
     */
    @java.lang.Override
    public int getRsrq() {
      return rsrq_;
    }
    /**
     * <pre>
     * RSRP in units of 0.1 dBm
     * </pre>
     *
     * <code>int32 rsrq = 6;</code>
     * @param value The rsrq to set.
     * @return This builder for chaining.
     */
    public Builder setRsrq(int value) {

      rsrq_ = value;
      bitField0_ |= 0x00000020;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * RSRP in units of 0.1 dBm
     * </pre>
     *
     * <code>int32 rsrq = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearRsrq() {
      bitField0_ = (bitField0_ & ~0x00000020);
      rsrq_ = 0;
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


    // @@protoc_insertion_point(builder_scope:helium.mapper_scan_result)
  }

  // @@protoc_insertion_point(class_scope:helium.mapper_scan_result)
  private static final com.helium.grpc.mobile.mapper_scan_result DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.mobile.mapper_scan_result();
  }

  public static com.helium.grpc.mobile.mapper_scan_result getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<mapper_scan_result>
      PARSER = new com.google.protobuf.AbstractParser<mapper_scan_result>() {
    @java.lang.Override
    public mapper_scan_result parsePartialFrom(
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

  public static com.google.protobuf.Parser<mapper_scan_result> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<mapper_scan_result> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.mobile.mapper_scan_result getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

