// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

/**
 * <pre>
 * witness report outputted by ingestor, tagged with received timestamp
 * </pre>
 *
 * Protobuf type {@code helium.poc_lora.lora_witness_ingest_report_v1}
 */
public final class lora_witness_ingest_report_v1 extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:helium.poc_lora.lora_witness_ingest_report_v1)
    lora_witness_ingest_report_v1OrBuilder {
private static final long serialVersionUID = 0L;
  // Use lora_witness_ingest_report_v1.newBuilder() to construct.
  private lora_witness_ingest_report_v1(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private lora_witness_ingest_report_v1() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new lora_witness_ingest_report_v1();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_witness_ingest_report_v1_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_witness_ingest_report_v1_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.lora_witness_ingest_report_v1.class, com.helium.grpc.lora_witness_ingest_report_v1.Builder.class);
  }

  public static final int RECEIVED_TIMESTAMP_FIELD_NUMBER = 1;
  private long receivedTimestamp_ = 0L;
  /**
   * <pre>
   * Timestamp at ingest in millis since unix epoch
   * </pre>
   *
   * <code>uint64 received_timestamp = 1;</code>
   * @return The receivedTimestamp.
   */
  @java.lang.Override
  public long getReceivedTimestamp() {
    return receivedTimestamp_;
  }

  public static final int REPORT_FIELD_NUMBER = 2;
  private com.helium.grpc.lora_witness_report_req_v1 report_;
  /**
   * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
   * @return Whether the report field is set.
   */
  @java.lang.Override
  public boolean hasReport() {
    return report_ != null;
  }
  /**
   * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
   * @return The report.
   */
  @java.lang.Override
  public com.helium.grpc.lora_witness_report_req_v1 getReport() {
    return report_ == null ? com.helium.grpc.lora_witness_report_req_v1.getDefaultInstance() : report_;
  }
  /**
   * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
   */
  @java.lang.Override
  public com.helium.grpc.lora_witness_report_req_v1OrBuilder getReportOrBuilder() {
    return report_ == null ? com.helium.grpc.lora_witness_report_req_v1.getDefaultInstance() : report_;
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
    if (receivedTimestamp_ != 0L) {
      output.writeUInt64(1, receivedTimestamp_);
    }
    if (report_ != null) {
      output.writeMessage(2, getReport());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (receivedTimestamp_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeUInt64Size(1, receivedTimestamp_);
    }
    if (report_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getReport());
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
    if (!(obj instanceof com.helium.grpc.lora_witness_ingest_report_v1)) {
      return super.equals(obj);
    }
    com.helium.grpc.lora_witness_ingest_report_v1 other = (com.helium.grpc.lora_witness_ingest_report_v1) obj;

    if (getReceivedTimestamp()
        != other.getReceivedTimestamp()) return false;
    if (hasReport() != other.hasReport()) return false;
    if (hasReport()) {
      if (!getReport()
          .equals(other.getReport())) return false;
    }
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
    hash = (37 * hash) + RECEIVED_TIMESTAMP_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getReceivedTimestamp());
    if (hasReport()) {
      hash = (37 * hash) + REPORT_FIELD_NUMBER;
      hash = (53 * hash) + getReport().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.lora_witness_ingest_report_v1 parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.lora_witness_ingest_report_v1 prototype) {
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
   * witness report outputted by ingestor, tagged with received timestamp
   * </pre>
   *
   * Protobuf type {@code helium.poc_lora.lora_witness_ingest_report_v1}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_lora.lora_witness_ingest_report_v1)
      com.helium.grpc.lora_witness_ingest_report_v1OrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_witness_ingest_report_v1_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_witness_ingest_report_v1_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.lora_witness_ingest_report_v1.class, com.helium.grpc.lora_witness_ingest_report_v1.Builder.class);
    }

    // Construct using com.helium.grpc.lora_witness_ingest_report_v1.newBuilder()
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
      receivedTimestamp_ = 0L;
      report_ = null;
      if (reportBuilder_ != null) {
        reportBuilder_.dispose();
        reportBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_lora_witness_ingest_report_v1_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.lora_witness_ingest_report_v1 getDefaultInstanceForType() {
      return com.helium.grpc.lora_witness_ingest_report_v1.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.lora_witness_ingest_report_v1 build() {
      com.helium.grpc.lora_witness_ingest_report_v1 result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.lora_witness_ingest_report_v1 buildPartial() {
      com.helium.grpc.lora_witness_ingest_report_v1 result = new com.helium.grpc.lora_witness_ingest_report_v1(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.lora_witness_ingest_report_v1 result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.receivedTimestamp_ = receivedTimestamp_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.report_ = reportBuilder_ == null
            ? report_
            : reportBuilder_.build();
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.lora_witness_ingest_report_v1) {
        return mergeFrom((com.helium.grpc.lora_witness_ingest_report_v1)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.lora_witness_ingest_report_v1 other) {
      if (other == com.helium.grpc.lora_witness_ingest_report_v1.getDefaultInstance()) return this;
      if (other.getReceivedTimestamp() != 0L) {
        setReceivedTimestamp(other.getReceivedTimestamp());
      }
      if (other.hasReport()) {
        mergeReport(other.getReport());
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
              receivedTimestamp_ = input.readUInt64();
              bitField0_ |= 0x00000001;
              break;
            } // case 8
            case 18: {
              input.readMessage(
                  getReportFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 18
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

    private long receivedTimestamp_ ;
    /**
     * <pre>
     * Timestamp at ingest in millis since unix epoch
     * </pre>
     *
     * <code>uint64 received_timestamp = 1;</code>
     * @return The receivedTimestamp.
     */
    @java.lang.Override
    public long getReceivedTimestamp() {
      return receivedTimestamp_;
    }
    /**
     * <pre>
     * Timestamp at ingest in millis since unix epoch
     * </pre>
     *
     * <code>uint64 received_timestamp = 1;</code>
     * @param value The receivedTimestamp to set.
     * @return This builder for chaining.
     */
    public Builder setReceivedTimestamp(long value) {

      receivedTimestamp_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Timestamp at ingest in millis since unix epoch
     * </pre>
     *
     * <code>uint64 received_timestamp = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearReceivedTimestamp() {
      bitField0_ = (bitField0_ & ~0x00000001);
      receivedTimestamp_ = 0L;
      onChanged();
      return this;
    }

    private com.helium.grpc.lora_witness_report_req_v1 report_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.helium.grpc.lora_witness_report_req_v1, com.helium.grpc.lora_witness_report_req_v1.Builder, com.helium.grpc.lora_witness_report_req_v1OrBuilder> reportBuilder_;
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     * @return Whether the report field is set.
     */
    public boolean hasReport() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     * @return The report.
     */
    public com.helium.grpc.lora_witness_report_req_v1 getReport() {
      if (reportBuilder_ == null) {
        return report_ == null ? com.helium.grpc.lora_witness_report_req_v1.getDefaultInstance() : report_;
      } else {
        return reportBuilder_.getMessage();
      }
    }
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     */
    public Builder setReport(com.helium.grpc.lora_witness_report_req_v1 value) {
      if (reportBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        report_ = value;
      } else {
        reportBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     */
    public Builder setReport(
        com.helium.grpc.lora_witness_report_req_v1.Builder builderForValue) {
      if (reportBuilder_ == null) {
        report_ = builderForValue.build();
      } else {
        reportBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     */
    public Builder mergeReport(com.helium.grpc.lora_witness_report_req_v1 value) {
      if (reportBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          report_ != null &&
          report_ != com.helium.grpc.lora_witness_report_req_v1.getDefaultInstance()) {
          getReportBuilder().mergeFrom(value);
        } else {
          report_ = value;
        }
      } else {
        reportBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     */
    public Builder clearReport() {
      bitField0_ = (bitField0_ & ~0x00000002);
      report_ = null;
      if (reportBuilder_ != null) {
        reportBuilder_.dispose();
        reportBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     */
    public com.helium.grpc.lora_witness_report_req_v1.Builder getReportBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getReportFieldBuilder().getBuilder();
    }
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     */
    public com.helium.grpc.lora_witness_report_req_v1OrBuilder getReportOrBuilder() {
      if (reportBuilder_ != null) {
        return reportBuilder_.getMessageOrBuilder();
      } else {
        return report_ == null ?
            com.helium.grpc.lora_witness_report_req_v1.getDefaultInstance() : report_;
      }
    }
    /**
     * <code>.helium.poc_lora.lora_witness_report_req_v1 report = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.helium.grpc.lora_witness_report_req_v1, com.helium.grpc.lora_witness_report_req_v1.Builder, com.helium.grpc.lora_witness_report_req_v1OrBuilder> 
        getReportFieldBuilder() {
      if (reportBuilder_ == null) {
        reportBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.helium.grpc.lora_witness_report_req_v1, com.helium.grpc.lora_witness_report_req_v1.Builder, com.helium.grpc.lora_witness_report_req_v1OrBuilder>(
                getReport(),
                getParentForChildren(),
                isClean());
        report_ = null;
      }
      return reportBuilder_;
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


    // @@protoc_insertion_point(builder_scope:helium.poc_lora.lora_witness_ingest_report_v1)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_lora.lora_witness_ingest_report_v1)
  private static final com.helium.grpc.lora_witness_ingest_report_v1 DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.lora_witness_ingest_report_v1();
  }

  public static com.helium.grpc.lora_witness_ingest_report_v1 getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<lora_witness_ingest_report_v1>
      PARSER = new com.google.protobuf.AbstractParser<lora_witness_ingest_report_v1>() {
    @java.lang.Override
    public lora_witness_ingest_report_v1 parsePartialFrom(
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

  public static com.google.protobuf.Parser<lora_witness_ingest_report_v1> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<lora_witness_ingest_report_v1> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.lora_witness_ingest_report_v1 getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
