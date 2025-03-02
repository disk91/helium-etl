// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_lora.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc;

/**
 * Protobuf type {@code helium.poc_lora.invalid_details}
 */
public final class invalid_details extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:helium.poc_lora.invalid_details)
    invalid_detailsOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      invalid_details.class.getName());
  }
  // Use invalid_details.newBuilder() to construct.
  private invalid_details(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private invalid_details() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_invalid_details_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_invalid_details_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.invalid_details.class, com.helium.grpc.invalid_details.Builder.class);
  }

  private int dataCase_ = 0;
  @SuppressWarnings("serial")
  private java.lang.Object data_;
  public enum DataCase
      implements com.google.protobuf.Internal.EnumLite,
          com.google.protobuf.AbstractMessage.InternalOneOfEnum {
    DENYLIST_TAG(1),
    DATA_NOT_SET(0);
    private final int value;
    private DataCase(int value) {
      this.value = value;
    }
    /**
     * @param value The number of the enum to look for.
     * @return The enum associated with the given number.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static DataCase valueOf(int value) {
      return forNumber(value);
    }

    public static DataCase forNumber(int value) {
      switch (value) {
        case 1: return DENYLIST_TAG;
        case 0: return DATA_NOT_SET;
        default: return null;
      }
    }
    public int getNumber() {
      return this.value;
    }
  };

  public DataCase
  getDataCase() {
    return DataCase.forNumber(
        dataCase_);
  }

  public static final int DENYLIST_TAG_FIELD_NUMBER = 1;
  /**
   * <code>string denylist_tag = 1;</code>
   * @return Whether the denylistTag field is set.
   */
  public boolean hasDenylistTag() {
    return dataCase_ == 1;
  }
  /**
   * <code>string denylist_tag = 1;</code>
   * @return The denylistTag.
   */
  public java.lang.String getDenylistTag() {
    java.lang.Object ref = "";
    if (dataCase_ == 1) {
      ref = data_;
    }
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (dataCase_ == 1) {
        data_ = s;
      }
      return s;
    }
  }
  /**
   * <code>string denylist_tag = 1;</code>
   * @return The bytes for denylistTag.
   */
  public com.google.protobuf.ByteString
      getDenylistTagBytes() {
    java.lang.Object ref = "";
    if (dataCase_ == 1) {
      ref = data_;
    }
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      if (dataCase_ == 1) {
        data_ = b;
      }
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (dataCase_ == 1) {
      com.google.protobuf.GeneratedMessage.writeString(output, 1, data_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (dataCase_ == 1) {
      size += com.google.protobuf.GeneratedMessage.computeStringSize(1, data_);
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
    if (!(obj instanceof com.helium.grpc.invalid_details)) {
      return super.equals(obj);
    }
    com.helium.grpc.invalid_details other = (com.helium.grpc.invalid_details) obj;

    if (!getDataCase().equals(other.getDataCase())) return false;
    switch (dataCase_) {
      case 1:
        if (!getDenylistTag()
            .equals(other.getDenylistTag())) return false;
        break;
      case 0:
      default:
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
    switch (dataCase_) {
      case 1:
        hash = (37 * hash) + DENYLIST_TAG_FIELD_NUMBER;
        hash = (53 * hash) + getDenylistTag().hashCode();
        break;
      case 0:
      default:
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.invalid_details parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.invalid_details parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.invalid_details parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.invalid_details parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.invalid_details parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.invalid_details parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.invalid_details parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.invalid_details parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.invalid_details parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.invalid_details parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.invalid_details parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.invalid_details parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.invalid_details prototype) {
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
   * Protobuf type {@code helium.poc_lora.invalid_details}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_lora.invalid_details)
      com.helium.grpc.invalid_detailsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_invalid_details_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_invalid_details_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.invalid_details.class, com.helium.grpc.invalid_details.Builder.class);
    }

    // Construct using com.helium.grpc.invalid_details.newBuilder()
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
      dataCase_ = 0;
      data_ = null;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.PocLoRa.internal_static_helium_poc_lora_invalid_details_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.invalid_details getDefaultInstanceForType() {
      return com.helium.grpc.invalid_details.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.invalid_details build() {
      com.helium.grpc.invalid_details result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.invalid_details buildPartial() {
      com.helium.grpc.invalid_details result = new com.helium.grpc.invalid_details(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      buildPartialOneofs(result);
      onBuilt();
      return result;
    }

    private void buildPartial0(com.helium.grpc.invalid_details result) {
      int from_bitField0_ = bitField0_;
    }

    private void buildPartialOneofs(com.helium.grpc.invalid_details result) {
      result.dataCase_ = dataCase_;
      result.data_ = this.data_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.invalid_details) {
        return mergeFrom((com.helium.grpc.invalid_details)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.invalid_details other) {
      if (other == com.helium.grpc.invalid_details.getDefaultInstance()) return this;
      switch (other.getDataCase()) {
        case DENYLIST_TAG: {
          dataCase_ = 1;
          data_ = other.data_;
          onChanged();
          break;
        }
        case DATA_NOT_SET: {
          break;
        }
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
              java.lang.String s = input.readStringRequireUtf8();
              dataCase_ = 1;
              data_ = s;
              break;
            } // case 10
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
    private int dataCase_ = 0;
    private java.lang.Object data_;
    public DataCase
        getDataCase() {
      return DataCase.forNumber(
          dataCase_);
    }

    public Builder clearData() {
      dataCase_ = 0;
      data_ = null;
      onChanged();
      return this;
    }

    private int bitField0_;

    /**
     * <code>string denylist_tag = 1;</code>
     * @return Whether the denylistTag field is set.
     */
    @java.lang.Override
    public boolean hasDenylistTag() {
      return dataCase_ == 1;
    }
    /**
     * <code>string denylist_tag = 1;</code>
     * @return The denylistTag.
     */
    @java.lang.Override
    public java.lang.String getDenylistTag() {
      java.lang.Object ref = "";
      if (dataCase_ == 1) {
        ref = data_;
      }
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (dataCase_ == 1) {
          data_ = s;
        }
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string denylist_tag = 1;</code>
     * @return The bytes for denylistTag.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getDenylistTagBytes() {
      java.lang.Object ref = "";
      if (dataCase_ == 1) {
        ref = data_;
      }
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        if (dataCase_ == 1) {
          data_ = b;
        }
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string denylist_tag = 1;</code>
     * @param value The denylistTag to set.
     * @return This builder for chaining.
     */
    public Builder setDenylistTag(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      dataCase_ = 1;
      data_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string denylist_tag = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDenylistTag() {
      if (dataCase_ == 1) {
        dataCase_ = 0;
        data_ = null;
        onChanged();
      }
      return this;
    }
    /**
     * <code>string denylist_tag = 1;</code>
     * @param value The bytes for denylistTag to set.
     * @return This builder for chaining.
     */
    public Builder setDenylistTagBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      dataCase_ = 1;
      data_ = value;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helium.poc_lora.invalid_details)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_lora.invalid_details)
  private static final com.helium.grpc.invalid_details DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.invalid_details();
  }

  public static com.helium.grpc.invalid_details getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<invalid_details>
      PARSER = new com.google.protobuf.AbstractParser<invalid_details>() {
    @java.lang.Override
    public invalid_details parsePartialFrom(
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

  public static com.google.protobuf.Parser<invalid_details> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<invalid_details> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.invalid_details getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

