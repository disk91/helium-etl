// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package com.helium.grpc;

public final class PocLoRa {
  private PocLoRa() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_non_rewardable_packet_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_non_rewardable_packet_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_beacon_report_req_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_beacon_report_req_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_beacon_report_resp_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_beacon_report_resp_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_witness_report_req_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_witness_report_req_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_witness_report_resp_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_witness_report_resp_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_beacon_ingest_report_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_beacon_ingest_report_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_witness_ingest_report_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_witness_ingest_report_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_valid_beacon_report_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_valid_beacon_report_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_valid_witness_report_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_valid_witness_report_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_invalid_details_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_invalid_details_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_invalid_beacon_report_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_invalid_beacon_report_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_invalid_witness_report_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_invalid_witness_report_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_verified_witness_report_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_verified_witness_report_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_lora_poc_v1_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_lora_poc_v1_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_gateway_reward_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_gateway_reward_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_operational_reward_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_operational_reward_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_helium_poc_lora_iot_reward_share_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_helium_poc_lora_iot_reward_share_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\026service/poc_lora.proto\022\017helium.poc_lor" +
      "a\032\017data_rate.proto\032\035service/packet_verif" +
      "ier.proto\"\237\001\n\025non_rewardable_packet\0224\n\006p" +
      "acket\030\001 \001(\0132$.helium.packet_verifier.val" +
      "id_packet\022=\n\006reason\030\002 \001(\0162-.helium.poc_l" +
      "ora.non_rewardable_packet_reason\022\021\n\ttime" +
      "stamp\030\003 \001(\004\"\370\001\n\031lora_beacon_report_req_v" +
      "1\022\017\n\007pub_key\030\002 \001(\014\022\025\n\rlocal_entropy\030\003 \001(" +
      "\014\022\026\n\016remote_entropy\030\004 \001(\014\022\014\n\004data\030\005 \001(\014\022" +
      "\021\n\tfrequency\030\006 \001(\004\022\017\n\007channel\030\007 \001(\005\022#\n\010d" +
      "atarate\030\010 \001(\0162\021.helium.data_rate\022\020\n\010tx_p" +
      "ower\030\t \001(\005\022\021\n\ttimestamp\030\n \001(\004\022\021\n\tsignatu" +
      "re\030\013 \001(\014\022\014\n\004tmst\030\014 \001(\r\"(\n\032lora_beacon_re" +
      "port_resp_v1\022\n\n\002id\030\001 \001(\t\"\304\001\n\032lora_witnes" +
      "s_report_req_v1\022\017\n\007pub_key\030\002 \001(\014\022\014\n\004data" +
      "\030\003 \001(\014\022\021\n\ttimestamp\030\004 \001(\004\022\014\n\004tmst\030\005 \001(\r\022" +
      "\016\n\006signal\030\006 \001(\021\022\013\n\003snr\030\007 \001(\005\022\021\n\tfrequenc" +
      "y\030\010 \001(\004\022#\n\010datarate\030\n \001(\0162\021.helium.data_" +
      "rate\022\021\n\tsignature\030\013 \001(\014\")\n\033lora_witness_" +
      "report_resp_v1\022\n\n\002id\030\001 \001(\t\"v\n\034lora_beaco" +
      "n_ingest_report_v1\022\032\n\022received_timestamp" +
      "\030\001 \001(\004\022:\n\006report\030\002 \001(\0132*.helium.poc_lora" +
      ".lora_beacon_report_req_v1\"x\n\035lora_witne" +
      "ss_ingest_report_v1\022\032\n\022received_timestam" +
      "p\030\001 \001(\004\022;\n\006report\030\002 \001(\0132+.helium.poc_lor" +
      "a.lora_witness_report_req_v1\"\320\001\n\033lora_va" +
      "lid_beacon_report_v1\022\032\n\022received_timesta" +
      "mp\030\001 \001(\004\022\020\n\010location\030\002 \001(\t\022\021\n\thex_scale\030" +
      "\003 \001(\r\022:\n\006report\030\004 \001(\0132*.helium.poc_lora." +
      "lora_beacon_report_req_v1\022\023\n\013reward_unit" +
      "\030\005 \001(\r\022\014\n\004gain\030\006 \001(\005\022\021\n\televation\030\007 \001(\005\"" +
      "\265\001\n\034lora_valid_witness_report_v1\022\032\n\022rece" +
      "ived_timestamp\030\001 \001(\004\022\020\n\010location\030\002 \001(\t\022\021" +
      "\n\thex_scale\030\003 \001(\r\022;\n\006report\030\004 \001(\0132+.heli" +
      "um.poc_lora.lora_witness_report_req_v1\022\023" +
      "\n\013reward_unit\030\005 \001(\r:\002\030\001\"1\n\017invalid_detai" +
      "ls\022\026\n\014denylist_tag\030\001 \001(\tH\000B\006\n\004data\"\226\002\n\035l" +
      "ora_invalid_beacon_report_v1\022\032\n\022received" +
      "_timestamp\030\001 \001(\004\022/\n\006reason\030\002 \001(\0162\037.heliu" +
      "m.poc_lora.invalid_reason\022:\n\006report\030\003 \001(" +
      "\0132*.helium.poc_lora.lora_beacon_report_r" +
      "eq_v1\022\020\n\010location\030\004 \001(\t\022\014\n\004gain\030\005 \001(\005\022\021\n" +
      "\televation\030\006 \001(\005\0229\n\017invalid_details\030\007 \001(" +
      "\0132 .helium.poc_lora.invalid_details\"\252\002\n\036" +
      "lora_invalid_witness_report_v1\022\032\n\022receiv" +
      "ed_timestamp\030\001 \001(\004\022/\n\006reason\030\002 \001(\0162\037.hel" +
      "ium.poc_lora.invalid_reason\022;\n\006report\030\003 " +
      "\001(\0132+.helium.poc_lora.lora_witness_repor" +
      "t_req_v1\022C\n\020participant_side\030\004 \001(\0162).hel" +
      "ium.poc_lora.invalid_participant_side\0229\n" +
      "\017invalid_details\030\005 \001(\0132 .helium.poc_lora" +
      ".invalid_details\"\304\003\n\037lora_verified_witne" +
      "ss_report_v1\022\032\n\022received_timestamp\030\001 \001(\004" +
      "\0224\n\006status\030\002 \001(\0162$.helium.poc_lora.verif" +
      "ication_status\022;\n\006report\030\003 \001(\0132+.helium." +
      "poc_lora.lora_witness_report_req_v1\022\020\n\010l" +
      "ocation\030\004 \001(\t\022\021\n\thex_scale\030\005 \001(\r\022\023\n\013rewa" +
      "rd_unit\030\006 \001(\r\0227\n\016invalid_reason\030\007 \001(\0162\037." +
      "helium.poc_lora.invalid_reason\022C\n\020partic" +
      "ipant_side\030\010 \001(\0162).helium.poc_lora.inval" +
      "id_participant_side\022\014\n\004gain\030\t \001(\005\022\021\n\tele" +
      "vation\030\n \001(\005\0229\n\017invalid_details\030\013 \001(\0132 ." +
      "helium.poc_lora.invalid_details\"\200\002\n\013lora" +
      "_poc_v1\022\016\n\006poc_id\030\001 \001(\014\022C\n\rbeacon_report" +
      "\030\002 \001(\0132,.helium.poc_lora.lora_valid_beac" +
      "on_report_v1\022L\n\022selected_witnesses\030\003 \003(\013" +
      "20.helium.poc_lora.lora_verified_witness" +
      "_report_v1\022N\n\024unselected_witnesses\030\004 \003(\013" +
      "20.helium.poc_lora.lora_verified_witness" +
      "_report_v1\"p\n\016gateway_reward\022\023\n\013hotspot_" +
      "key\030\001 \001(\014\022\025\n\rbeacon_amount\030\002 \001(\004\022\026\n\016witn" +
      "ess_amount\030\003 \001(\004\022\032\n\022dc_transfer_amount\030\004" +
      " \001(\004\"$\n\022operational_reward\022\016\n\006amount\030\001 \001" +
      "(\004\"\304\001\n\020iot_reward_share\022\024\n\014start_period\030" +
      "\001 \001(\004\022\022\n\nend_period\030\002 \001(\004\0229\n\016gateway_rew" +
      "ard\030\003 \001(\0132\037.helium.poc_lora.gateway_rewa" +
      "rdH\000\022A\n\022operational_reward\030\004 \001(\0132#.heliu" +
      "m.poc_lora.operational_rewardH\000B\010\n\006rewar" +
      "d*-\n\023verification_status\022\t\n\005valid\020\000\022\013\n\007i" +
      "nvalid\020\001*D\n\030invalid_participant_side\022\r\n\t" +
      "side_none\020\000\022\014\n\010beaconer\020\002\022\013\n\007witness\020\003*\260" +
      "\003\n\016invalid_reason\022\017\n\013reason_none\020\000\022\021\n\rba" +
      "d_signature\020\001\022\020\n\014not_asserted\020\002\022\023\n\017entro" +
      "py_expired\020\003\022\017\n\013bad_entropy\020\004\022\026\n\022invalid" +
      "_capability\020\005\022\022\n\016invalid_pubkey\020\006\022\026\n\022irr" +
      "egular_interval\020\007\022\025\n\021gateway_not_found\020\010" +
      "\022\n\n\006denied\020\t\022\022\n\016invalid_packet\020\n\022\014\n\010bad_" +
      "rssi\020\013\022\022\n\016invalid_region\020\014\022\031\n\025max_distan" +
      "ce_exceeded\020\r\022\025\n\021invalid_frequency\020\016\022\020\n\014" +
      "self_witness\020\017\022\t\n\005stale\020\020\022\034\n\030scaling_fac" +
      "tor_not_found\020\021\022\021\n\runknown_error\020\022\022\026\n\022be" +
      "low_min_distance\020\023\022\r\n\tduplicate\020\024*@\n\034non" +
      "_rewardable_packet_reason\022 \n\034gateway_not" +
      "_found_for_packet\020\0002\353\001\n\010poc_lora\022m\n\022subm" +
      "it_lora_beacon\022*.helium.poc_lora.lora_be" +
      "acon_report_req_v1\032+.helium.poc_lora.lor" +
      "a_beacon_report_resp_v1\022p\n\023submit_lora_w" +
      "itness\022+.helium.poc_lora.lora_witness_re" +
      "port_req_v1\032,.helium.poc_lora.lora_witne" +
      "ss_report_resp_v1B\034\n\017com.helium.grpcB\007Po" +
      "cLoRaP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          PocLoRa.getDescriptor(),
          PacketVerifier.getDescriptor(),
        });
    internal_static_helium_poc_lora_non_rewardable_packet_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_helium_poc_lora_non_rewardable_packet_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_non_rewardable_packet_descriptor,
        new String[] { "Packet", "Reason", "Timestamp", });
    internal_static_helium_poc_lora_lora_beacon_report_req_v1_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_helium_poc_lora_lora_beacon_report_req_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_beacon_report_req_v1_descriptor,
        new String[] { "PubKey", "LocalEntropy", "RemoteEntropy", "Data", "Frequency", "Channel", "Datarate", "TxPower", "Timestamp", "Signature", "Tmst", });
    internal_static_helium_poc_lora_lora_beacon_report_resp_v1_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_helium_poc_lora_lora_beacon_report_resp_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_beacon_report_resp_v1_descriptor,
        new String[] { "Id", });
    internal_static_helium_poc_lora_lora_witness_report_req_v1_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_helium_poc_lora_lora_witness_report_req_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_witness_report_req_v1_descriptor,
        new String[] { "PubKey", "Data", "Timestamp", "Tmst", "Signal", "Snr", "Frequency", "Datarate", "Signature", });
    internal_static_helium_poc_lora_lora_witness_report_resp_v1_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_helium_poc_lora_lora_witness_report_resp_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_witness_report_resp_v1_descriptor,
        new String[] { "Id", });
    internal_static_helium_poc_lora_lora_beacon_ingest_report_v1_descriptor =
      getDescriptor().getMessageTypes().get(5);
    internal_static_helium_poc_lora_lora_beacon_ingest_report_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_beacon_ingest_report_v1_descriptor,
        new String[] { "ReceivedTimestamp", "Report", });
    internal_static_helium_poc_lora_lora_witness_ingest_report_v1_descriptor =
      getDescriptor().getMessageTypes().get(6);
    internal_static_helium_poc_lora_lora_witness_ingest_report_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_witness_ingest_report_v1_descriptor,
        new String[] { "ReceivedTimestamp", "Report", });
    internal_static_helium_poc_lora_lora_valid_beacon_report_v1_descriptor =
      getDescriptor().getMessageTypes().get(7);
    internal_static_helium_poc_lora_lora_valid_beacon_report_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_valid_beacon_report_v1_descriptor,
        new String[] { "ReceivedTimestamp", "Location", "HexScale", "Report", "RewardUnit", "Gain", "Elevation", });
    internal_static_helium_poc_lora_lora_valid_witness_report_v1_descriptor =
      getDescriptor().getMessageTypes().get(8);
    internal_static_helium_poc_lora_lora_valid_witness_report_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_valid_witness_report_v1_descriptor,
        new String[] { "ReceivedTimestamp", "Location", "HexScale", "Report", "RewardUnit", });
    internal_static_helium_poc_lora_invalid_details_descriptor =
      getDescriptor().getMessageTypes().get(9);
    internal_static_helium_poc_lora_invalid_details_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_invalid_details_descriptor,
        new String[] { "DenylistTag", "Data", });
    internal_static_helium_poc_lora_lora_invalid_beacon_report_v1_descriptor =
      getDescriptor().getMessageTypes().get(10);
    internal_static_helium_poc_lora_lora_invalid_beacon_report_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_invalid_beacon_report_v1_descriptor,
        new String[] { "ReceivedTimestamp", "Reason", "Report", "Location", "Gain", "Elevation", "InvalidDetails", });
    internal_static_helium_poc_lora_lora_invalid_witness_report_v1_descriptor =
      getDescriptor().getMessageTypes().get(11);
    internal_static_helium_poc_lora_lora_invalid_witness_report_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_invalid_witness_report_v1_descriptor,
        new String[] { "ReceivedTimestamp", "Reason", "Report", "ParticipantSide", "InvalidDetails", });
    internal_static_helium_poc_lora_lora_verified_witness_report_v1_descriptor =
      getDescriptor().getMessageTypes().get(12);
    internal_static_helium_poc_lora_lora_verified_witness_report_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_verified_witness_report_v1_descriptor,
        new String[] { "ReceivedTimestamp", "Status", "Report", "Location", "HexScale", "RewardUnit", "InvalidReason", "ParticipantSide", "Gain", "Elevation", "InvalidDetails", });
    internal_static_helium_poc_lora_lora_poc_v1_descriptor =
      getDescriptor().getMessageTypes().get(13);
    internal_static_helium_poc_lora_lora_poc_v1_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_lora_poc_v1_descriptor,
        new String[] { "PocId", "BeaconReport", "SelectedWitnesses", "UnselectedWitnesses", });
    internal_static_helium_poc_lora_gateway_reward_descriptor =
      getDescriptor().getMessageTypes().get(14);
    internal_static_helium_poc_lora_gateway_reward_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_gateway_reward_descriptor,
        new String[] { "HotspotKey", "BeaconAmount", "WitnessAmount", "DcTransferAmount", });
    internal_static_helium_poc_lora_operational_reward_descriptor =
      getDescriptor().getMessageTypes().get(15);
    internal_static_helium_poc_lora_operational_reward_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_operational_reward_descriptor,
        new String[] { "Amount", });
    internal_static_helium_poc_lora_iot_reward_share_descriptor =
      getDescriptor().getMessageTypes().get(16);
    internal_static_helium_poc_lora_iot_reward_share_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_helium_poc_lora_iot_reward_share_descriptor,
        new String[] { "StartPeriod", "EndPeriod", "GatewayReward", "OperationalReward", "Reward", });
    PocLoRa.getDescriptor();
    PacketVerifier.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
