// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/packet_verifier.proto

// Protobuf Java Version: 3.25.1
package com.helium.grpc;

public final class PacketVerifier {
  private PacketVerifier() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_packet_verifier_valid_packet_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_packet_verifier_valid_packet_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_packet_verifier_invalid_packet_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_packet_verifier_invalid_packet_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_packet_verifier_valid_data_transfer_session_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_packet_verifier_valid_data_transfer_session_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035service/packet_verifier.proto\022\026helium." +
      "packet_verifier\"v\n\014valid_packet\022\024\n\014paylo" +
      "ad_size\030\001 \001(\r\022\017\n\007gateway\030\002 \001(\014\022\024\n\014payloa" +
      "d_hash\030\003 \001(\014\022\017\n\007num_dcs\030\004 \001(\r\022\030\n\020packet_" +
      "timestamp\030\005 \001(\004\"\214\001\n\016invalid_packet\022\024\n\014pa" +
      "yload_size\030\001 \001(\r\022\017\n\007gateway\030\002 \001(\014\022\024\n\014pay" +
      "load_hash\030\003 \001(\014\022=\n\006reason\030\004 \001(\0162-.helium" +
      ".packet_verifier.invalid_packet_reason\"\307" +
      "\001\n\033valid_data_transfer_session\022\017\n\007pub_ke" +
      "y\030\001 \001(\014\022\024\n\014upload_bytes\030\002 \001(\004\022\026\n\016downloa" +
      "d_bytes\030\003 \001(\004\022\017\n\007num_dcs\030\004 \001(\004\022\r\n\005payer\030" +
      "\005 \001(\014\022\027\n\017first_timestamp\030\006 \001(\004\022\026\n\016last_t" +
      "imestamp\030\007 \001(\004\022\030\n\020rewardable_bytes\030\010 \001(\004" +
      "*G\n\025invalid_packet_reason\022.\n*invalid_pac" +
      "ket_reason_insufficient_balance\020\000B#\n\017com" +
      ".helium.grpcB\016PacketVerifierP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_helium_packet_verifier_valid_packet_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helium_packet_verifier_valid_packet_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_packet_verifier_valid_packet_descriptor,
        new java.lang.String[] { "PayloadSize", "Gateway", "PayloadHash", "NumDcs", "PacketTimestamp", });
    internal_static_helium_packet_verifier_invalid_packet_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helium_packet_verifier_invalid_packet_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_packet_verifier_invalid_packet_descriptor,
        new java.lang.String[] { "PayloadSize", "Gateway", "PayloadHash", "Reason", });
    internal_static_helium_packet_verifier_valid_data_transfer_session_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_helium_packet_verifier_valid_data_transfer_session_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_packet_verifier_valid_data_transfer_session_descriptor,
        new java.lang.String[] { "PubKey", "UploadBytes", "DownloadBytes", "NumDcs", "Payer", "FirstTimestamp", "LastTimestamp", "RewardableBytes", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
