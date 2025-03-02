// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: service/poc_mobile.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

/**
 * Protobuf type {@code helium.poc_mobile.processed_files}
 */
public final class processed_files extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:helium.poc_mobile.processed_files)
    processed_filesOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      processed_files.class.getName());
  }
  // Use processed_files.newBuilder() to construct.
  private processed_files(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private processed_files() {
    files_ = java.util.Collections.emptyList();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_processed_files_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_processed_files_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.helium.grpc.mobile.processed_files.class, com.helium.grpc.mobile.processed_files.Builder.class);
  }

  public static final int FILES_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private java.util.List<com.helium.grpc.mobile.file_info> files_;
  /**
   * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
   */
  @java.lang.Override
  public java.util.List<com.helium.grpc.mobile.file_info> getFilesList() {
    return files_;
  }
  /**
   * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.helium.grpc.mobile.file_infoOrBuilder> 
      getFilesOrBuilderList() {
    return files_;
  }
  /**
   * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
   */
  @java.lang.Override
  public int getFilesCount() {
    return files_.size();
  }
  /**
   * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
   */
  @java.lang.Override
  public com.helium.grpc.mobile.file_info getFiles(int index) {
    return files_.get(index);
  }
  /**
   * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
   */
  @java.lang.Override
  public com.helium.grpc.mobile.file_infoOrBuilder getFilesOrBuilder(
      int index) {
    return files_.get(index);
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
    for (int i = 0; i < files_.size(); i++) {
      output.writeMessage(1, files_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < files_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, files_.get(i));
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
    if (!(obj instanceof com.helium.grpc.mobile.processed_files)) {
      return super.equals(obj);
    }
    com.helium.grpc.mobile.processed_files other = (com.helium.grpc.mobile.processed_files) obj;

    if (!getFilesList()
        .equals(other.getFilesList())) return false;
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
    if (getFilesCount() > 0) {
      hash = (37 * hash) + FILES_FIELD_NUMBER;
      hash = (53 * hash) + getFilesList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.helium.grpc.mobile.processed_files parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.helium.grpc.mobile.processed_files parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.helium.grpc.mobile.processed_files parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static com.helium.grpc.mobile.processed_files parseFrom(
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
  public static Builder newBuilder(com.helium.grpc.mobile.processed_files prototype) {
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
   * Protobuf type {@code helium.poc_mobile.processed_files}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:helium.poc_mobile.processed_files)
      com.helium.grpc.mobile.processed_filesOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_processed_files_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_processed_files_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.helium.grpc.mobile.processed_files.class, com.helium.grpc.mobile.processed_files.Builder.class);
    }

    // Construct using com.helium.grpc.mobile.processed_files.newBuilder()
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
      if (filesBuilder_ == null) {
        files_ = java.util.Collections.emptyList();
      } else {
        files_ = null;
        filesBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.helium.grpc.mobile.PocMobile.internal_static_helium_poc_mobile_processed_files_descriptor;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.processed_files getDefaultInstanceForType() {
      return com.helium.grpc.mobile.processed_files.getDefaultInstance();
    }

    @java.lang.Override
    public com.helium.grpc.mobile.processed_files build() {
      com.helium.grpc.mobile.processed_files result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.helium.grpc.mobile.processed_files buildPartial() {
      com.helium.grpc.mobile.processed_files result = new com.helium.grpc.mobile.processed_files(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(com.helium.grpc.mobile.processed_files result) {
      if (filesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          files_ = java.util.Collections.unmodifiableList(files_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.files_ = files_;
      } else {
        result.files_ = filesBuilder_.build();
      }
    }

    private void buildPartial0(com.helium.grpc.mobile.processed_files result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.helium.grpc.mobile.processed_files) {
        return mergeFrom((com.helium.grpc.mobile.processed_files)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.helium.grpc.mobile.processed_files other) {
      if (other == com.helium.grpc.mobile.processed_files.getDefaultInstance()) return this;
      if (filesBuilder_ == null) {
        if (!other.files_.isEmpty()) {
          if (files_.isEmpty()) {
            files_ = other.files_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureFilesIsMutable();
            files_.addAll(other.files_);
          }
          onChanged();
        }
      } else {
        if (!other.files_.isEmpty()) {
          if (filesBuilder_.isEmpty()) {
            filesBuilder_.dispose();
            filesBuilder_ = null;
            files_ = other.files_;
            bitField0_ = (bitField0_ & ~0x00000001);
            filesBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 getFilesFieldBuilder() : null;
          } else {
            filesBuilder_.addAllMessages(other.files_);
          }
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
              com.helium.grpc.mobile.file_info m =
                  input.readMessage(
                      com.helium.grpc.mobile.file_info.parser(),
                      extensionRegistry);
              if (filesBuilder_ == null) {
                ensureFilesIsMutable();
                files_.add(m);
              } else {
                filesBuilder_.addMessage(m);
              }
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
    private int bitField0_;

    private java.util.List<com.helium.grpc.mobile.file_info> files_ =
      java.util.Collections.emptyList();
    private void ensureFilesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        files_ = new java.util.ArrayList<com.helium.grpc.mobile.file_info>(files_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        com.helium.grpc.mobile.file_info, com.helium.grpc.mobile.file_info.Builder, com.helium.grpc.mobile.file_infoOrBuilder> filesBuilder_;

    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public java.util.List<com.helium.grpc.mobile.file_info> getFilesList() {
      if (filesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(files_);
      } else {
        return filesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public int getFilesCount() {
      if (filesBuilder_ == null) {
        return files_.size();
      } else {
        return filesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public com.helium.grpc.mobile.file_info getFiles(int index) {
      if (filesBuilder_ == null) {
        return files_.get(index);
      } else {
        return filesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder setFiles(
        int index, com.helium.grpc.mobile.file_info value) {
      if (filesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFilesIsMutable();
        files_.set(index, value);
        onChanged();
      } else {
        filesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder setFiles(
        int index, com.helium.grpc.mobile.file_info.Builder builderForValue) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        files_.set(index, builderForValue.build());
        onChanged();
      } else {
        filesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder addFiles(com.helium.grpc.mobile.file_info value) {
      if (filesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFilesIsMutable();
        files_.add(value);
        onChanged();
      } else {
        filesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder addFiles(
        int index, com.helium.grpc.mobile.file_info value) {
      if (filesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureFilesIsMutable();
        files_.add(index, value);
        onChanged();
      } else {
        filesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder addFiles(
        com.helium.grpc.mobile.file_info.Builder builderForValue) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        files_.add(builderForValue.build());
        onChanged();
      } else {
        filesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder addFiles(
        int index, com.helium.grpc.mobile.file_info.Builder builderForValue) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        files_.add(index, builderForValue.build());
        onChanged();
      } else {
        filesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder addAllFiles(
        java.lang.Iterable<? extends com.helium.grpc.mobile.file_info> values) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, files_);
        onChanged();
      } else {
        filesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder clearFiles() {
      if (filesBuilder_ == null) {
        files_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        filesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public Builder removeFiles(int index) {
      if (filesBuilder_ == null) {
        ensureFilesIsMutable();
        files_.remove(index);
        onChanged();
      } else {
        filesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public com.helium.grpc.mobile.file_info.Builder getFilesBuilder(
        int index) {
      return getFilesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public com.helium.grpc.mobile.file_infoOrBuilder getFilesOrBuilder(
        int index) {
      if (filesBuilder_ == null) {
        return files_.get(index);  } else {
        return filesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public java.util.List<? extends com.helium.grpc.mobile.file_infoOrBuilder> 
         getFilesOrBuilderList() {
      if (filesBuilder_ != null) {
        return filesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(files_);
      }
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public com.helium.grpc.mobile.file_info.Builder addFilesBuilder() {
      return getFilesFieldBuilder().addBuilder(
          com.helium.grpc.mobile.file_info.getDefaultInstance());
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public com.helium.grpc.mobile.file_info.Builder addFilesBuilder(
        int index) {
      return getFilesFieldBuilder().addBuilder(
          index, com.helium.grpc.mobile.file_info.getDefaultInstance());
    }
    /**
     * <code>repeated .helium.poc_mobile.file_info files = 1;</code>
     */
    public java.util.List<com.helium.grpc.mobile.file_info.Builder> 
         getFilesBuilderList() {
      return getFilesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        com.helium.grpc.mobile.file_info, com.helium.grpc.mobile.file_info.Builder, com.helium.grpc.mobile.file_infoOrBuilder> 
        getFilesFieldBuilder() {
      if (filesBuilder_ == null) {
        filesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            com.helium.grpc.mobile.file_info, com.helium.grpc.mobile.file_info.Builder, com.helium.grpc.mobile.file_infoOrBuilder>(
                files_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        files_ = null;
      }
      return filesBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:helium.poc_mobile.processed_files)
  }

  // @@protoc_insertion_point(class_scope:helium.poc_mobile.processed_files)
  private static final com.helium.grpc.mobile.processed_files DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.helium.grpc.mobile.processed_files();
  }

  public static com.helium.grpc.mobile.processed_files getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<processed_files>
      PARSER = new com.google.protobuf.AbstractParser<processed_files>() {
    @java.lang.Override
    public processed_files parsePartialFrom(
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

  public static com.google.protobuf.Parser<processed_files> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<processed_files> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.helium.grpc.mobile.processed_files getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

