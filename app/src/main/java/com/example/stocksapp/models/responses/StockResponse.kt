package com.example.stocksapp.models.responses

import com.google.gson.annotations.SerializedName

data class StockResponse(

	@field:SerializedName("data")
	val data: ArrayList<DataItem?>? = null,

	@field:SerializedName("response_type")
	val responseType: String? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("client_id")
	val clientId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: Long? = null
)

data class DataItem(

	@field:SerializedName("symbol")
	val symbol: String? = null,

	@field:SerializedName("holdings_update_qty")
	val holdingsUpdateQty: Int? = null,

	@field:SerializedName("product")
	val product: String? = null,

	@field:SerializedName("quantity")
	val quantity: Int? = null,

	@field:SerializedName("collateral_type")
	val collateralType: String? = null,

	@field:SerializedName("t1_holding_qty")
	val t1HoldingQty: Int? = null,

	@field:SerializedName("collateral_update_qty")
	val collateralUpdateQty: Double? = null,

	@field:SerializedName("token_nse")
	val tokenNse: String? = null,

	@field:SerializedName("ltp")
	val ltp: Double? = null,

	@field:SerializedName("avg_price")
	val avgPrice: String? = null,

	@field:SerializedName("cnc_used_quantity")
	val cncUsedQuantity: Int? = null,

	@field:SerializedName("haircut")
	val haircut: Double? = null,

	@field:SerializedName("collateral_qty")
	val collateralQty: Int? = null,

	@field:SerializedName("withheld_collateral_qty")
	val withheldCollateralQty: Int? = null,

	@field:SerializedName("company_name")
	val companyName: String? = null,

	@field:SerializedName("token_bse")
	val tokenBse: String? = null,

	@field:SerializedName("close")
	val close: Double? = null,

	@field:SerializedName("isin")
	val isin: String? = null,

	@field:SerializedName("withheld_holding_qty")
	val withheldHoldingQty: Int? = null
)
