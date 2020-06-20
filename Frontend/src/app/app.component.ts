import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"],
})

export class AppComponent implements OnInit {
  title = "spMgAngDocker";
  constructor(private http: HttpClient) {}
  data: any = [];
  ngOnInit() {
    this.getData();
  }
  getData() {
    let url = "http://localhost:8080/getItems";
    this.http.get(url).subscribe((res) => {
      console.log(res);
      this.data = res;
    });
  }
  addItem() {
    let url = "http://localhost:8080/addItem";
    let data = { itemName: "new item" };
    this.http
      .post(url, data, {
        headers: {
          "Content-Type": "application/json",
        },
      })
      .subscribe((res) => {
        console.log(res);
        this.getData();
      });
  }
}
