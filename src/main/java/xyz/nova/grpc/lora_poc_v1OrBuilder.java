// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: service/poc_lora.proto

package xyz.nova.grpc;

public interface lora_poc_v1OrBuilder extends
    // @@protoc_insertion_point(interface_extends:helium.poc_lora.lora_poc_v1)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bytes poc_id = 1;</code>
   * @return The pocId.
   */
  com.google.protobuf.ByteString getPocId();

  /**
   * <code>.helium.poc_lora.lora_valid_beacon_report_v1 beacon_report = 2;</code>
   * @return Whether the beaconReport field is set.
   */
  boolean hasBeaconReport();
  /**
   * <code>.helium.poc_lora.lora_valid_beacon_report_v1 beacon_report = 2;</code>
   * @return The beaconReport.
   */
  xyz.nova.grpc.lora_valid_beacon_report_v1 getBeaconReport();
  /**
   * <code>.helium.poc_lora.lora_valid_beacon_report_v1 beacon_report = 2;</code>
   */
  xyz.nova.grpc.lora_valid_beacon_report_v1OrBuilder getBeaconReportOrBuilder();

  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 selected_witnesses = 3;</code>
   */
  java.util.List<xyz.nova.grpc.lora_verified_witness_report_v1> 
      getSelectedWitnessesList();
  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 selected_witnesses = 3;</code>
   */
  xyz.nova.grpc.lora_verified_witness_report_v1 getSelectedWitnesses(int index);
  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 selected_witnesses = 3;</code>
   */
  int getSelectedWitnessesCount();
  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 selected_witnesses = 3;</code>
   */
  java.util.List<? extends xyz.nova.grpc.lora_verified_witness_report_v1OrBuilder> 
      getSelectedWitnessesOrBuilderList();
  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 selected_witnesses = 3;</code>
   */
  xyz.nova.grpc.lora_verified_witness_report_v1OrBuilder getSelectedWitnessesOrBuilder(
      int index);

  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 unselected_witnesses = 4;</code>
   */
  java.util.List<xyz.nova.grpc.lora_verified_witness_report_v1> 
      getUnselectedWitnessesList();
  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 unselected_witnesses = 4;</code>
   */
  xyz.nova.grpc.lora_verified_witness_report_v1 getUnselectedWitnesses(int index);
  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 unselected_witnesses = 4;</code>
   */
  int getUnselectedWitnessesCount();
  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 unselected_witnesses = 4;</code>
   */
  java.util.List<? extends xyz.nova.grpc.lora_verified_witness_report_v1OrBuilder> 
      getUnselectedWitnessesOrBuilderList();
  /**
   * <code>repeated .helium.poc_lora.lora_verified_witness_report_v1 unselected_witnesses = 4;</code>
   */
  xyz.nova.grpc.lora_verified_witness_report_v1OrBuilder getUnselectedWitnessesOrBuilder(
      int index);
}
