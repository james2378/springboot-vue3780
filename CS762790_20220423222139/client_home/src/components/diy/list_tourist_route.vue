<template>
	<div class="diy_home diy_list diy_tourist_route" id="diy_tourist_route_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/tourist_route/details?tourist_route_id=' + o['tourist_route_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/tourist_route/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/tourist_route/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
					<th class="diy_title" v-if="$check_field('get','place_of_departure')">出发地
					</th>
					<th class="diy_title" v-if="$check_field('get','destination')">目的地
					</th>
					<th class="diy_title" v-if="$check_field('get','mode_of_transportation')">交通方式
					</th>
					<th class="diy_title" v-if="$check_field('get','tourism_type')">旅游类型
					</th>
					<th class="diy_title" v-if="$check_field('get','destination_scenery')">终点风景
					</th>
					<th class="diy_title" v-if="$check_field('get','travel_time')">旅行时间
					</th>
					<th class="diy_title" v-if="$check_field('get','total_cost')">总体花费
					</th>
					<th class="diy_title" v-if="$check_field('get','travel_route')">行程路线
					</th>
				</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
					<td class="diy_field diy_text" v-if="$check_field('get','place_of_departure')">
						<span>
							{{ o["place_of_departure"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','destination')">
						<span>
							{{ o["destination"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','mode_of_transportation')">
						<span>
							{{ o["mode_of_transportation"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','tourism_type')">
						<span>
							{{ o["tourism_type"] }}
						</span>
					</td>
					<td class="diy_field" v-if="$check_field('get','destination_scenery')">
						<img class="diy_img" :src="o['destination_scenery']" />
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','travel_time')">
						<span>
							{{ o["travel_time"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','total_cost')">
						<span>
							{{ o["total_cost"] }}
						</span>
					</td>
					<td class="diy_field diy_text" v-if="$check_field('get','travel_route')">
						<span>
							{{ o["travel_route"] }}
						</span>
					</td>
				</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						{
							title: "终点风景",
							name: "destination_scenery",
							type: "图片",
							is_img_list: "1"
						},
						],
						itemList: [
								{
									title: "出发地",
									name: "place_of_departure",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "目的地",
									name: "destination",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "交通方式",
									name: "mode_of_transportation",
									type: "下寻",
									is_img_list: "0"
								},
								{
									title: "旅游类型",
									name: "tourism_type",
									type: "下寻",
									is_img_list: "0"
								},
								{
									title: "旅行时间",
									name: "travel_time",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "总体花费",
									name: "total_cost",
									type: "文本",
									is_img_list: "0"
								},
						],
						richList: [
								{
									title: "行程路线",
									name: "travel_route",
									type: "多文本"
								},
								{
									title: "旅途详情",
									name: "travel_details",
									type: "编辑"
								},
						],
			};
		},
		methods: {
		},
		created() {
		},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

