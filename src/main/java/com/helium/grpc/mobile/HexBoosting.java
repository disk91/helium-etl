// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: hex_boosting.proto

// Protobuf Java Version: 3.25.1
package com.helium.grpc.mobile;

public final class HexBoosting {
  private HexBoosting() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_boosted_hex_info_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_boosted_hex_info_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_boosted_hex_update_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_boosted_hex_update_v1_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022hex_boosting.proto\022\006helium\"\277\001\n\023boosted" +
      "_hex_info_v1\022\020\n\010location\030\001 \001(\004\022\020\n\010start_" +
      "ts\030\002 \001(\004\022\016\n\006end_ts\030\003 \001(\004\022\025\n\rperiod_lengt" +
      "h\030\004 \001(\r\022\023\n\013multipliers\030\005 \003(\r\022\032\n\022boosted_" +
      "hex_pubkey\030\006 \001(\014\022\033\n\023boost_config_pubkey\030" +
      "\007 \001(\014\022\017\n\007version\030\010 \001(\r\"W\n\025boosted_hex_up" +
      "date_v1\022\021\n\ttimestamp\030\001 \001(\004\022+\n\006update\030\002 \001" +
      "(\0132\033.helium.boosted_hex_info_v1B\'\n\026com.h" +
      "elium.grpc.mobileB\013HexBoostingP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_helium_boosted_hex_info_v1_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helium_boosted_hex_info_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_boosted_hex_info_v1_descriptor,
        new java.lang.String[] { "Location", "StartTs", "EndTs", "PeriodLength", "Multipliers", "BoostedHexPubkey", "BoostConfigPubkey", "Version", });
    internal_static_helium_boosted_hex_update_v1_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helium_boosted_hex_update_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_boosted_hex_update_v1_descriptor,
        new java.lang.String[] { "Timestamp", "Update", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}