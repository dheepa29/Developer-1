import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { trigger, transition, style, animate } from '@angular/animations';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  animations: [
    trigger('fileUploaded', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('1s', style({ opacity: 1 })), // Animation for 1 second
      ]),
    ]),
  ],
})
export class DashboardComponent {

  selectedFile: File | undefined;
  selectedFileName: string | undefined;
  showSuccessMessage = false; // Add this property for animation


  constructor(private http: HttpClient) { }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
    this.selectedFileName = this.selectedFile ? this.selectedFile.name : undefined;
    this.showSuccessMessage = false; // Reset the success message flag
  }

  uploadFile(): void {
    if (this.selectedFile) {

      // if (!this.selectedFile.name.endsWith('.csv')) {
      //   console.error('Selected file is not a CSV file.');
      //   alert('Selected file is not a CSV file.');
      //   return;
      // }

      const formData: FormData = new FormData();
      formData.append('file', this.selectedFile, this.selectedFile.name);

      this.http.post('http://localhost:8080/customer/upload', formData)
        .subscribe({
          next: (response) => {
            console.log('File uploaded successfully:', response);
            this.showSuccessMessage = true; // Trigger the success animation
            setTimeout(() => {
              this.selectedFile = undefined; // Reset the selected file
              this.selectedFileName = undefined;
              this.showSuccessMessage = false; // Hide the success message
            }, 1000); // Reset after 1 second
          },
          error: (error) => {
            console.error('File upload failed:', error);
          }
        });
    }
  }

}
