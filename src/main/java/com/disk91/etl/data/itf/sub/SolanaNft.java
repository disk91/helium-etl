package com.disk91.etl.data.itf.sub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SolanaNft {

    protected String name;
    protected String mint;
    protected String owner;     // Solana Wallet

    protected SolanaNftAttribute attributes;    // Hnt hotspot ID

    // ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMint() {
        return mint;
    }

    public void setMint(String mint) {
        this.mint = mint;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public SolanaNftAttribute getAttributes() {
        return attributes;
    }

    public void setAttributes(SolanaNftAttribute attributes) {
        this.attributes = attributes;
    }



    /*
    "nfts": [
      {
        "name": "restless-punch-buffalo",
        "symbol": "HOTSPOT",
        "royalty": 0,
        "image_uri": "https://shdw-drive.genesysgo.net/6t...P/hotspot.png",
        "cached_image_uri": "https://shdw-drive.genesysgo.net/6tv...WP/hotspot.png",
        "animation_url": "",
        "cached_animation_url": "",
        "metadata_uri": "https://entities.nft.helium.io/1Q...n",
        "description": "A Hotspot NFT on Helium",
        "mint": "F4Te3K",
        "owner": "6Vu7FJ6",
        "update_authority": "5Ev8m",
        "creators": [
          {
            "address": "Fvxv48H",
            "share": 100,
            "verified": true
          }
        ],
        "collection": {
          "address": "ACfKsV",
          "verified": true
        },
        "attributes": {
          "ecc_compact": "11jaSQn",
          "rewardable": true
        },
        "attributes_array": [
          {
            "trait_type": "ecc_compact",
            "value": "11jaSQn"
          },
          {
            "trait_type": "rewardable",
            "value": true
          }
        ],
        "external_url": "",
        "primary_sale_happened": true,
        "is_mutable": true,
        "token_standard": "NonFungible",
        "is_loaded_metadata": true,
        "is_compressed": true,
        "merkle_tree": "8nyQLJWQp4vYYxi"
      },
      {
        "name": "qer",
        "symbol": "HOTSPOT",
        "royalty": 0,
        "image_uri": "htotspot.png",
        "cached_image_uri": "https:/otspot.png",
        "animation_url": "",
        "cached_animation_url": "",
     */
}
