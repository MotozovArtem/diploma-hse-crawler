<template>
  <v-container>
    <v-row>
      <v-col>
        <v-text-field label="ID" v-model="webPortal.id" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Portal Name" v-model="webPortal.portalName" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Domain" v-model="webPortal.domainName" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Creation Time" v-model="webPortal.creationTime" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-text-field label="Last Modified Time" v-model="webPortal.lastModifiedTime" readonly/>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-list two-line subheader >
          <v-subheader >{{ `Collected pages: ${webPortal.webPages.length}` }}</v-subheader>
          <v-list-item v-for="webPage in webPortal.webPages" :key="webPage.id">
            <v-list-item-content>
              <v-list-item-title >{{ webPage.name }}</v-list-item-title>
              <v-list-item-subtitle v-text="webPage.id"/>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-col>
    </v-row>
  </v-container>
</template>

<script lang="ts">
import {Component, Prop, Vue} from "vue-property-decorator";
import {WebPortal} from "@/model/model";

@Component
export default class WebPortalItem extends Vue {
  @Prop({type: String}) readonly id!: string;

  private webPortalId: string  = '';
  private webPortal: WebPortal = null;

  private async created(): Promise<void> {
    this.webPortalId = this.id;
    await this.loadObject();
  }

  private async loadObject(): Promise<void> {
    this.webPortal = await this.$api.getDomainObject({name: "web_portal_view", id: this.webPortalId})
  }

}
</script>

<style scoped>

</style>
