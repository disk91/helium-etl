// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: mapper.proto
// Protobuf Java Version: 4.29.2

package com.helium.grpc.mobile;

public final class MapperClass {
  private MapperClass() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 2,
      /* suffix= */ "",
      MapperClass.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_attach_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_attach_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_attach_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_attach_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_attach_v1_mapper_attach_candidate_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_attach_v1_mapper_attach_candidate_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_gps_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_gps_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_gps_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_gps_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_scan_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_scan_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_scan_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_scan_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_mapper_scan_result_descriptor;
  static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_helium_mapper_scan_result_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014mapper.proto\022\006helium\"\272\001\n\006mapper\022!\n\003gps" +
      "\030\001 \001(\0132\022.helium.mapper_gpsH\000\022#\n\004scan\030\002 \001" +
      "(\0132\023.helium.mapper_scanH\000\022\'\n\006attach\030\003 \001(" +
      "\0132\025.helium.mapper_attachH\000\022\021\n\tsignature\030" +
      "\004 \001(\014\022\017\n\007pub_key\030\005 \001(\014\022\020\n\010hotspots\030\006 \003(\014" +
      "B\t\n\007message\"I\n\rmapper_attach\022-\n\tattach_v" +
      "1\030\001 \001(\0132\030.helium.mapper_attach_v1H\000B\t\n\007v" +
      "ersion\"\261\004\n\020mapper_attach_v1\022\026\n\016attach_co" +
      "unter\030\001 \001(\r\022\"\n\003gps\030\002 \001(\0132\025.helium.mapper" +
      "_gps_v1\022C\n\tcandidate\030\003 \001(\01320.helium.mapp" +
      "er_attach_v1.mapper_attach_candidate\022=\n\006" +
      "result\030\004 \001(\0162-.helium.mapper_attach_v1.m" +
      "apper_attach_result\032\360\001\n\027mapper_attach_ca" +
      "ndidate\022H\n\004type\030\001 \001(\0162:.helium.mapper_at" +
      "tach_v1.mapper_attach_candidate.cell_tec" +
      "h\022\032\n\022from_scan_response\030\002 \001(\r\022\r\n\005delay\030\003" +
      " \001(\r\022\014\n\004plmn\030\004 \001(\r\022\013\n\003fcn\030\005 \001(\r\022\013\n\003cid\030\006" +
      " \001(\004\022\014\n\004rsrp\030\007 \001(\005\022\014\n\004rsrq\030\010 \001(\005\"\034\n\tcell" +
      "_tech\022\007\n\003lte\020\000\022\006\n\002nr\020\001\"j\n\024mapper_attach_" +
      "result\022\010\n\004NONE\020\000\022\013\n\007CONNECT\020\001\022\013\n\007LIMSERV" +
      "\020\002\022\n\n\006NOCONN\020\003\022\n\n\006SEARCH\020\004\022\026\n\022NO_NETWORK" +
      "_SERVICE\020\005\"@\n\nmapper_gps\022\'\n\006gps_v1\030\001 \001(\013" +
      "2\025.helium.mapper_gps_v1H\000B\t\n\007version\"}\n\r" +
      "mapper_gps_v1\022\021\n\tunix_time\030\001 \001(\r\022\013\n\003lat\030" +
      "\002 \001(\005\022\013\n\003lon\030\003 \001(\005\022\014\n\004hdop\030\004 \001(\r\022\020\n\010alti" +
      "tude\030\005 \001(\003\022\020\n\010num_sats\030\006 \001(\005\022\r\n\005speed\030\007 " +
      "\001(\r\"C\n\013mapper_scan\022)\n\007scan_v1\030\001 \001(\0132\026.he" +
      "lium.mapper_scan_v1H\000B\t\n\007version\"w\n\016mapp" +
      "er_scan_v1\022\024\n\014scan_counter\030\001 \001(\r\022\"\n\003gps\030" +
      "\002 \001(\0132\025.helium.mapper_gps_v1\022+\n\007results\030" +
      "\003 \003(\0132\032.helium.mapper_scan_result\"e\n\022map" +
      "per_scan_result\022\013\n\003cid\030\001 \001(\004\022\014\n\004plmn\030\002 \001" +
      "(\r\022\013\n\003fcn\030\003 \001(\r\022\013\n\003pci\030\004 \001(\r\022\014\n\004rsrp\030\005 \001" +
      "(\005\022\014\n\004rsrq\030\006 \001(\005B\'\n\026com.helium.grpc.mobi" +
      "leB\013MapperClassP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_helium_mapper_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helium_mapper_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_descriptor,
        new java.lang.String[] { "Gps", "Scan", "Attach", "Signature", "PubKey", "Hotspots", "Message", });
    internal_static_helium_mapper_attach_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helium_mapper_attach_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_attach_descriptor,
        new java.lang.String[] { "AttachV1", "Version", });
    internal_static_helium_mapper_attach_v1_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_helium_mapper_attach_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_attach_v1_descriptor,
        new java.lang.String[] { "AttachCounter", "Gps", "Candidate", "Result", });
    internal_static_helium_mapper_attach_v1_mapper_attach_candidate_descriptor =
      internal_static_helium_mapper_attach_v1_descriptor.getNestedTypes().get(0);
    internal_static_helium_mapper_attach_v1_mapper_attach_candidate_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_attach_v1_mapper_attach_candidate_descriptor,
        new java.lang.String[] { "Type", "FromScanResponse", "Delay", "Plmn", "Fcn", "Cid", "Rsrp", "Rsrq", });
    internal_static_helium_mapper_gps_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_helium_mapper_gps_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_gps_descriptor,
        new java.lang.String[] { "GpsV1", "Version", });
    internal_static_helium_mapper_gps_v1_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_helium_mapper_gps_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_gps_v1_descriptor,
        new java.lang.String[] { "UnixTime", "Lat", "Lon", "Hdop", "Altitude", "NumSats", "Speed", });
    internal_static_helium_mapper_scan_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_helium_mapper_scan_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_scan_descriptor,
        new java.lang.String[] { "ScanV1", "Version", });
    internal_static_helium_mapper_scan_v1_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_helium_mapper_scan_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_scan_v1_descriptor,
        new java.lang.String[] { "ScanCounter", "Gps", "Results", });
    internal_static_helium_mapper_scan_result_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_helium_mapper_scan_result_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_helium_mapper_scan_result_descriptor,
        new java.lang.String[] { "Cid", "Plmn", "Fcn", "Pci", "Rsrp", "Rsrq", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
