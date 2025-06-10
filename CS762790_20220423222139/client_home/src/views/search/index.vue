<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="风景资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_name"
				title="普通用户用户姓名"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_gender"
				title="普通用户用户性别"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/mode_of_transportation/list', 'get')"
				:list="result_mode_of_transportation_mode_of_transportation"
				title="交通方式交通方式"
				source_table="mode_of_transportation"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/tourism_type/list', 'get')"
				:list="result_tourism_type_tourism_type"
				title="旅游类型旅游类型"
				source_table="tourism_type"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/tourist_route/list', 'get')"
				:list="result_tourist_route_place_of_departure"
				title="旅游路线出发地"
				source_table="tourist_route"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/tourist_route/list', 'get')"
				:list="result_tourist_route_destination"
				title="旅游路线目的地"
				source_table="tourist_route"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/tourist_route/list', 'get')"
				:list="result_tourist_route_mode_of_transportation"
				title="旅游路线交通方式"
				source_table="tourist_route"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/tourist_route/list', 'get')"
				:list="result_tourist_route_tourism_type"
				title="旅游路线旅游类型"
				source_table="tourist_route"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_ordinary_users_user_name":[],
			"result_ordinary_users_user_gender":[],
			"result_mode_of_transportation_mode_of_transportation":[],
			"result_tourism_type_tourism_type":[],
			"result_tourist_route_place_of_departure":[],
			"result_tourist_route_destination":[],
			"result_tourist_route_mode_of_transportation":[],
			"result_tourist_route_tourism_type":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_ordinary_users_user_name(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_name = json.result.list;
			result_ordinary_users_user_name.map(o => o.title = o['user_name'])
	  			this.result_ordinary_users_user_name = result_ordinary_users_user_name
		 	}
		});
	},
	/**
	 * 获取user_gender
	 */
	get_ordinary_users_user_gender(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "user_gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_gender = json.result.list;
			result_ordinary_users_user_gender.map(o => o.title = o['user_gender'])
	  			this.result_ordinary_users_user_gender = result_ordinary_users_user_gender
		 	}
		});
	},
	/**
	 * 获取mode_of_transportation
	 */
	get_mode_of_transportation_mode_of_transportation(){
		this.$get("~/api/mode_of_transportation/get_list?like=0", { page: 1, size: 10, "mode_of_transportation": this.query.word }, (json) => {
		  if (json.result) {
			var result_mode_of_transportation_mode_of_transportation = json.result.list;
			result_mode_of_transportation_mode_of_transportation.map(o => o.title = o['mode_of_transportation'])
	  			this.result_mode_of_transportation_mode_of_transportation = result_mode_of_transportation_mode_of_transportation
		 	}
		});
	},
	/**
	 * 获取tourism_type
	 */
	get_tourism_type_tourism_type(){
		this.$get("~/api/tourism_type/get_list?like=0", { page: 1, size: 10, "tourism_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_tourism_type_tourism_type = json.result.list;
			result_tourism_type_tourism_type.map(o => o.title = o['tourism_type'])
	  			this.result_tourism_type_tourism_type = result_tourism_type_tourism_type
		 	}
		});
	},
	/**
	 * 获取place_of_departure
	 */
	get_tourist_route_place_of_departure(){
		this.$get("~/api/tourist_route/get_list?like=0", { page: 1, size: 10, "place_of_departure": this.query.word }, (json) => {
		  if (json.result) {
			var result_tourist_route_place_of_departure = json.result.list;
			result_tourist_route_place_of_departure.map(o => o.title = o['place_of_departure'])
	  			this.result_tourist_route_place_of_departure = result_tourist_route_place_of_departure
		 	}
		});
	},
	/**
	 * 获取destination
	 */
	get_tourist_route_destination(){
		this.$get("~/api/tourist_route/get_list?like=0", { page: 1, size: 10, "destination": this.query.word }, (json) => {
		  if (json.result) {
			var result_tourist_route_destination = json.result.list;
			result_tourist_route_destination.map(o => o.title = o['destination'])
	  			this.result_tourist_route_destination = result_tourist_route_destination
		 	}
		});
	},
	/**
	 * 获取mode_of_transportation
	 */
	get_tourist_route_mode_of_transportation(){
		this.$get("~/api/tourist_route/get_list?like=0", { page: 1, size: 10, "mode_of_transportation": this.query.word }, (json) => {
		  if (json.result) {
			var result_tourist_route_mode_of_transportation = json.result.list;
			result_tourist_route_mode_of_transportation.map(o => o.title = o['mode_of_transportation'])
	  			this.result_tourist_route_mode_of_transportation = result_tourist_route_mode_of_transportation
		 	}
		});
	},
	/**
	 * 获取tourism_type
	 */
	get_tourist_route_tourism_type(){
		this.$get("~/api/tourist_route/get_list?like=0", { page: 1, size: 10, "tourism_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_tourist_route_tourism_type = json.result.list;
			result_tourist_route_tourism_type.map(o => o.title = o['tourism_type'])
	  			this.result_tourist_route_tourism_type = result_tourist_route_tourism_type
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_ordinary_users_user_name();
		this.get_ordinary_users_user_gender();
		this.get_mode_of_transportation_mode_of_transportation();
		this.get_tourism_type_tourism_type();
		this.get_tourist_route_place_of_departure();
		this.get_tourist_route_destination();
		this.get_tourist_route_mode_of_transportation();
		this.get_tourist_route_tourism_type();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_ordinary_users_user_name();
	  this.get_ordinary_users_user_gender();
	  this.get_mode_of_transportation_mode_of_transportation();
	  this.get_tourism_type_tourism_type();
	  this.get_tourist_route_place_of_departure();
	  this.get_tourist_route_destination();
	  this.get_tourist_route_mode_of_transportation();
	  this.get_tourist_route_tourism_type();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
